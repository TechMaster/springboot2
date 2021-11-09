import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MyHTTPServer {


   /**
    * Threads to handle incoming http requests
    */
   private static final int CONNECTION_POOL_THREADS = 100;

   /**
    * Hardcoded 501 HTML return
    */
   private static final String HTML501NOT_IMPLEMENTED_RETURN = "<HTML>\r\n <HEAD><TITLE>Not Implemented</TITLE>\r\n </HEAD>\r\n <BODY> <H1>HTTP Error 501: Not Implemented</H1>\r\n </BODY></HTML>\r\n";

   /**
    * 501 response code header
    */
   private static final String HTTP501RESPONSE_CODE = "HTTP/1.0 501 Not Implemented";

   /**
    * Hardcoded 404 HTML return
    */
   private static final String HTML404NOT_FOUND_RETURN = "<HTML>\r\n <HEAD><TITLE>File Not Found</TITLE>\r\n </HEAD>\r\n <BODY> <H1>HTTP Error 404: File Not Found</H1>\r\n </BODY></HTML>\r\n";

   /**
    * 404 response code header
    */
   private static final String HTTP404RESPONSE_CODE = "HTTP/1.0 404 File Not Found";

   /**
    * 200 response code header
    */
   private static final String HTTP200RESPONSE_CODE = "HTTP/1.0 200 OK";

   /**
    * Content type for errors
    */
   private static final String ERROR_REPSONSE_CONTENT_TYPE = "text/html; charset=utf-8";

   /**
    * Server index file
    */
   private static final String INDEX = "index.html";

   /**
    * Port the server is listening on
    */
   private final int port;

   /**
    * Initializes the http server, assigning port and configuring the logger
    * 
    * @param port The port the http server will listen on
    * @throws IOException if there are IO problems opening the file for the logger
    */
   public MyHTTPServer(int port) throws IOException {
      this.port = port;

   }

   /**
    * Starts up the server, initializing the thread pool, whose threads will handle
    * incoming requests Most of this is from the course text, modified to only use
    * port instead of messing/intializing a root directory, keeping it simple.
    */
   public void start() {
      ExecutorService pool = Executors.newFixedThreadPool(CONNECTION_POOL_THREADS); // init thread pool
      try (ServerSocket server = new ServerSocket(this.port)) { // setup server socket
         System.out.println("Accepting connections on port " + server.getLocalPort()); // don't need to log, just to
                                                                                       // console
         while (true) {
            try {
               Socket connection = server.accept(); // get socket
               pool.submit(new HTTPHandler(connection, System.currentTimeMillis())); // process request on thread
            } catch (IOException ex) {
               System.out.println(ex);
            } catch (RuntimeException ex) {
               System.out.println(ex);
            }
         }
      } catch (IOException ex) {
         System.out.println(ex);
      }
   }

   /**
    * Callable invoked in the thread pool to handle servers incoming http request.
    * Modified from the original to track the original accesstime as well as the
    * response code and response size. Once the file is served or an error page
    * displayed, an access log is written with the information.
    *
    * @author Tyler Matthews
    */
   private class HTTPHandler implements Callable<Void> {

      /**
       * The socket with the request connection
       */
      private final Socket connection;

      /**
       * The initial access time
       */
      private final long accessTime;

      /**
       * The response code to be returned in the response
       */
      private int code;

      /**
       * The byte size of the response
       */
      private long responseSize;

      /**
       * Initializes the http handler with the connection and initial access time
       *
       * @param connection The socket connection
       * @param accessTime The initial access time
       */
      public HTTPHandler(Socket connection, long accessTime) {
         this.connection = connection;
         this.accessTime = accessTime;
      }

      /**
       * Handles the http request by parsing the request and either serving a file or
       * returning an appropriate error
       */
      @Override
      public Void call() throws IOException {
         // setup input/output streams
         try (BufferedOutputStream rawOutput = new BufferedOutputStream(connection.getOutputStream());
               InputStream in = new BufferedInputStream(connection.getInputStream())) {
            HTTPRequestContainer httpRequest = new HTTPRequestContainer(getRequest(in)); // parses out the request line
                                                                                         // for easy lookup
            if (httpRequest.isGET()) { // if a valid get, try to serve file
               serveFile(rawOutput, httpRequest.file, httpRequest);
            } else { // else error, server only supports get
               writeError(rawOutput, HttpURLConnection.HTTP_NOT_IMPLEMENTED, HTML501NOT_IMPLEMENTED_RETURN,
                     httpRequest);
            }
           
            rawOutput.flush(); // flush the output before return
         } catch (IOException ex) {
            System.out.println(ex);
         } finally {
            connection.close();
         }
         return null;
      }

      /**
       * Attempts to serve the requested file. If no file specified, the index is
       * served.
       *
       * @param rawOutput   The output stream to write file to
       * @param fileName    The name of the file
       * @param httpRequest The request
       * @throws IOException On stream write or file access exception
       */
      private void serveFile(BufferedOutputStream rawOutput, String fileName, HTTPRequestContainer httpRequest)
            throws IOException {
         File file = new File(correctPath(fileName)); // if no file is specified we want the index, correct
         if (file.canRead()) { // check if file is accessbile
            // jhttp
            byte[] fileBytes = Files.readAllBytes(file.toPath()); // need to premptively read, so we can get the byte
                                                                  // size for response header
            writeHeaderIfApplicable(rawOutput, HttpURLConnection.HTTP_OK, getContentType(fileName), fileBytes.length,
                  httpRequest); // write header
            rawOutput.write(fileBytes); // write file
         } else {
            writeError(rawOutput, HttpURLConnection.HTTP_NOT_FOUND, HTML404NOT_FOUND_RETURN, httpRequest); // if file
                                                                                                           // inaccessbile,
                                                                                                           // write 404
                                                                                                           // page
         }
      }

      /**
       * Corrects file path, removing initial / or defaulting file to index
       *
       * @param file The file requested
       * @return The corrected file path for lookup
       */
      private String correctPath(String file) {
         // replace root path, server assumes index at server directory
         if (file != null && file.startsWith("/")) {
            file = file.replace("/", "");
         }
         if (file == null || file.trim().isEmpty() || file.equals("/")) {
            file = INDEX;
         }
         return file;
      }

      /**
       * Write out a hard coded html message as a response
       *
       * @param out             The output stream
       * @param responseIntCode The response code
       * @param error           The hardcoded html page to return
       * @param httpRequest     The initial request
       * @throws IOException on write
       */
      private void writeError(BufferedOutputStream out, int responseIntCode, String error,
            HTTPRequestContainer httpRequest) throws IOException {
         writeHeaderIfApplicable(out, responseIntCode, ERROR_REPSONSE_CONTENT_TYPE, error.length(), httpRequest);
         out.write(error.getBytes());
      }

      /**
       * A container to hold the request information, after being pased from the
       * request.
       * 
       * @author Tyler Matthews
       */
      private class HTTPRequestContainer {
         /**
          * The http method of the request
          */
         private String method;

         /**
          * The request line
          */
         private String requestLine;

         /**
          * The requested file
          */
         private String file;

         /**
          * The http version if specified
          */
         private String version;

         /**
          * Initializes a container by parsing out the request line
          * 
          * @param requestLine The request line
          */
         public HTTPRequestContainer(String requestLine) {
            this.requestLine = requestLine;
            String[] tokens = requestLine.split(" ");
            method = tokens[0];
            file = tokens[1];
            // client may not be 1+http
            if (tokens.length > 2) {
               version = tokens[2];
            }
         }

         /**
          * Is the request method GET
          * 
          * @return true if GET, false if not
          */
         public boolean isGET() {
            return method.equals("GET");
         }
      }

      /**
       * Returns the request line
       * 
       * @param in The inputstream to read the request line from
       * @return The request line
       * @throws IOException On read
       */
      private String getRequest(InputStream in) throws IOException {
         StringBuilder request = new StringBuilder();
         while (true) { // read it in
            int c = in.read();
            if (c == '\r' || c == '\n' || c == -1)
               break;
            request.append((char) c);
         }
         System.out.println(connection.getRemoteSocketAddress() + " " + request.toString()); // don't log, just to
                                                                                             // console
         return request.toString();
      }

      /**
       * Writes out a response header if the request was at least http 1, assigns the
       * code and response size for later logging
       * 
       * @param out           The output stream to write the eheader
       * @param code          The response code
       * @param contentType   The content type of the response
       * @param length        The length in bytes of the response
       * @param httpContainer The intial request container
       * @throws IOException on write
       */
      private void writeHeaderIfApplicable(BufferedOutputStream out, int code, String contentType, long length,
            HTTPRequestContainer httpContainer) throws IOException {
         if (httpContainer.version.startsWith("HTTP/")) { // if http 1+
            // write out a header
            out.write((getStringResponse(code) + "\r\nDate: " + new Date()
                  + "\r\nServer: MyHTTPServer 1.0\r\nContent-length: " + length + "\r\nContent-type: " + contentType
                  + "\r\n\r\n").getBytes());
         }
         // retain response codes/response length for later logging
         this.code = code;
         this.responseSize = length;
      }

      /**
       * Returns the response code string based on the integer ... played with
       * includiing this in the actual header as a method param, but decided this was
       * a little cleaner.
       * 
       * @param code The response code
       * @return The string response code line
       */
      private String getStringResponse(int code) {
         String stringResponse;
         switch (code) {
            case HttpURLConnection.HTTP_NOT_IMPLEMENTED:
               stringResponse = HTTP501RESPONSE_CODE;
               break;
            default:
            case HttpURLConnection.HTTP_NOT_FOUND:
               stringResponse = HTTP404RESPONSE_CODE;
               break;
            case HttpURLConnection.HTTP_OK:
               stringResponse = HTTP200RESPONSE_CODE;
               break;
         }
         return stringResponse;
      }

      /**
       * Attempt to return the content type for the given file
       * 
       * @param fileName The file to determine the content type of
       * @return The content type or empty string if not determined
       */
      private String getContentType(String fileName) {
         String contentType = URLConnection.getFileNameMap().getContentTypeFor(fileName);
         return contentType == null ? "" : contentType;
      }
   }

   /**
    * Starts up the http server on the provided port.
    * 
    * @param args
    */
   public static void main(String[] args) {
      // set the port to listen on, default to 80
      int port;
      try {
         port = Integer.parseInt(args[0]);
         if (port < 1 || port > 65535)
            port = 8080;
      } catch (RuntimeException ex) {
         port = 8080;
      }
      try {
         MyHTTPServer server = new MyHTTPServer(port); // create server
         server.start(); // start
      } catch (ArrayIndexOutOfBoundsException ex) {
         System.out.println("Usage: java MyHTTPServer port");
      } catch (IOException ex) {
         System.out.println(ex);
      }
   }
}
package vn.techmaster.tinyrest;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.lang.reflect.Method;

import vn.techmaster.tinyrest.annotation.ResponseBody;

public class App {
    public static void main(String[] args) {
        HttpServer server;
        try {
            server = HttpServer.create(new InetSocketAddress(8080), 0);
            server.createContext("/", new MyHandler());
            server.createContext("/film", new FilmHandler());
            server.setExecutor(null); // creates a default executor
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            String response = "<h1>Hello World</h1>";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    static class FilmHandler implements HttpHandler {
        @Override
        @ResponseBody(value = "XML")
        public void handle(HttpExchange t) throws IOException {
            //Demo đọc được attribute trong annotation
            Method method = new Object(){}.getClass().getEnclosingMethod();
            ResponseBody a = method.getAnnotation(ResponseBody.class);
            String responseType = a.value();
            System.out.println(responseType);

            List<Film> films = new ArrayList<>();
            films.add(new Film("Bố Già", "Trấn Thành"));
            films.add(new Film("Squid Games", "Bong Jin Heok"));

            Gson g = new Gson();
            String response = g.toJson(films);
            t.getResponseHeaders().add("Content-type", "application/json");
            final byte[] rawResponseBody = response.getBytes(StandardCharsets.UTF_8);
            t.getResponseHeaders().add("Content-length", Integer.toString(rawResponseBody.length));
            t.sendResponseHeaders(200, rawResponseBody.length);

            t.getResponseBody().write(rawResponseBody);
            t.close();
        }
    }
}

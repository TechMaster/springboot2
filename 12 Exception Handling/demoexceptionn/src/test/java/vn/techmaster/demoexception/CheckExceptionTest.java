package vn.techmaster.demoexception;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CheckExceptionTest {

	@Test
	// Hàm này sẽ throws Exception
	void unhandle_IOException() throws IOException {
		File file = new File("src/test/resources");
		String absolutePath = file.getAbsolutePath();
		Path path = Paths.get(absolutePath, "demo2.txt");

		List<String> lines = Files.readAllLines(path);
		lines.forEach(System.out::println);
		System.out.println("Continue to this line");
	}

	@Test
	// Hàm này
	void handle_IOException() {
		File file = new File("src/test/resources");
		String absolutePath = file.getAbsolutePath();
		Path path = Paths.get(absolutePath, "demo2.txt");

		try {
			List<String> lines = Files.readAllLines(path);
			lines.forEach(System.out::println);
		} catch (IOException e) {
			// e.printStackTrace();
		}
		System.out.println("Continue to this line");
	}

	@Test
	// Sử dụng AssertJ để kiểm tra exception throw
	void assertThowsException() {
		File file = new File("src/test/resources");
		String absolutePath = file.getAbsolutePath();
		Path path = Paths.get(absolutePath, "demo2.txt");

		assertThatThrownBy(() -> {
			List<String> lines = Files.readAllLines(path);
			lines.forEach(System.out::println);
		}).isInstanceOf(IOException.class);
	}

	@Test
	void testParseException() {
		String dateStr = "2011-11-19";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = dateFormat.parse(dateStr);
			System.out.println(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testClassNotFound() {
		String className = "vn.techmaster.demoexception.Foo";
		try {
			Class x = Class.forName(className);
			assertThat(x.getName()).isEqualTo(className);

			Class y = ClassLoader.getSystemClassLoader().loadClass(className);
			assertThat(x.getName()).isEqualTo(className);

			ClassLoader.getPlatformClassLoader().loadClass(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testClone() {
		try {
			new Foo().clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testREST() {
		try {

			URL url = new URL("https://jsonplaceholder.typicode.com/posts");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

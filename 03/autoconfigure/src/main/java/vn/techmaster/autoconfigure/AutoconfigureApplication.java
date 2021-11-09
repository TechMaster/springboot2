package vn.techmaster.autoconfigure;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AutoconfigureApplication {

	public static void main(String[] args) {
		//SpringApplication.run(AutoconfigureApplication.class, args);

		SpringApplication app = new SpringApplication(AutoconfigureApplication.class);
		//app.setDefaultProperties(Collections.singletonMap("server.port", "9001"));
		app.run(args);
	}
}

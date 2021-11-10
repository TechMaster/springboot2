package vn.techmaster.hiaop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class HiaopApplication {

	public static void main(String[] args) {
		SpringApplication.run(HiaopApplication.class, args);
	}

}

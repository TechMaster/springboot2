package vn.techmaster.fluxmongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
//@EnableWebFlux
public class FluxmongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(FluxmongoApplication.class, args);
	}

}

package vn.techmaster.gwcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;

import reactor.core.publisher.Mono;

@SpringBootApplication
public class GwcodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(GwcodeApplication.class, args);
	}

	@Bean
	public KeyResolver userKeyResolver() {
		return exchange -> Mono.just("1");
	}
}

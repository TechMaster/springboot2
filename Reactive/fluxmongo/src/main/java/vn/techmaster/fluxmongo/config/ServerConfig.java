package vn.techmaster.fluxmongo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;

import reactor.core.publisher.Mono;
import vn.techmaster.fluxmongo.exception.PostNotFoundException;

@Configuration
public class ServerConfig {
 /* @Bean
  public WebExceptionHandler exceptionHandler() {
    return (ServerWebExchange exchange, Throwable ex) -> {
        if (ex instanceof PostNotFoundException) {
            exchange.getResponse().setStatusCode(HttpStatus.NOT_FOUND);
            return exchange.getResponse().setComplete();
        }
        return Mono.error(ex);
    };
  }*/
}

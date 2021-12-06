package vn.techmaster.autoconfigure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import vn.techmaster.autoconfigure.component.Bar;
import vn.techmaster.autoconfigure.component.Foo;


@Configuration
public class Config {
  @Profile("dev")
	@Bean
  public Foo foo() {
    return new Foo();
  }

  @Profile("prod")
	@Bean
  public Bar bar() {
    return new Bar();
  }  
}

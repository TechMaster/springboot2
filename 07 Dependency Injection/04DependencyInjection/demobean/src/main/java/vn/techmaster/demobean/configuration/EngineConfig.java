package vn.techmaster.demobean.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import vn.techmaster.demobean.bean.ElectricEngine;
import vn.techmaster.demobean.bean.GasEngine;
import vn.techmaster.demobean.bean.HybridEngine;
import vn.techmaster.demobean.interfaces.Engine;


@Configuration
public class EngineConfig {
  @Bean
  public Engine gasEngine() {
    return new GasEngine();
  }

  @Bean(name = "electricEngine")
  public Engine electricEngine() {
    return new ElectricEngine();
  }

  @Bean(name = "hybridEngine")
  public Engine hybridEngine() {
    return new HybridEngine();
  }
}

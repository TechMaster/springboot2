package vn.techmaster.differentdi.configuration;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import vn.techmaster.differentdi.component.Keyboard;
import vn.techmaster.differentdi.component.MemoryStick;
import vn.techmaster.differentdi.component.Mouse;
import vn.techmaster.differentdi.interfaces.USB;

@Configuration
public class PCConfiguration {
  @Bean
  public List<USB> usbDevices() {
      return Arrays.asList(new Mouse(), new Keyboard(), new MemoryStick());
  }
}

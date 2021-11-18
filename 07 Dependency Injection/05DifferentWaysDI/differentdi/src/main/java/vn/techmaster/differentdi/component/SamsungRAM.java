package vn.techmaster.differentdi.component;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import vn.techmaster.differentdi.interfaces.DDR3;

@Component
@Primary
public class SamsungRAM implements DDR3 {
  
}

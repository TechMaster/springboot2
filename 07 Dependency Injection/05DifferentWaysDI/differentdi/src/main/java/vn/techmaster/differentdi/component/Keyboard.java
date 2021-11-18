package vn.techmaster.differentdi.component;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import vn.techmaster.differentdi.interfaces.USB1;

@Component("keyboard")
@Order(5)
public class Keyboard implements USB1 {
  public Keyboard() {
    System.out.println("construct Keyboard");
  }
}

package vn.techmaster.differentdi.component;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import vn.techmaster.differentdi.interfaces.USB1;

@Component("mouse")
@Order(2)
public class Mouse implements USB1{
  public Mouse() {
    System.out.println("construct Mouse");
  }
}

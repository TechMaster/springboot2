package vn.techmaster.differentdi.component;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import vn.techmaster.differentdi.interfaces.USB2;
@Component
@Order(3)
public class WebCam implements USB2 {
  public WebCam() {
    System.out.println("construct Web cam");
  }
}

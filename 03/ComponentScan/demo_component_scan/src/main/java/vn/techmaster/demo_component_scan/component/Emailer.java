package vn.techmaster.demo_component_scan.component;

import org.springframework.stereotype.Component;

@Component
public class Emailer {
  public void sendEmail() {
    System.out.println("send email");
  }
}

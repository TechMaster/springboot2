package vn.techmaster.differentdi.component;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import vn.techmaster.differentdi.interfaces.Sata;

@Component("fujitsu")
@Order(1)
public class FujitsuHDD implements Sata {
  
}

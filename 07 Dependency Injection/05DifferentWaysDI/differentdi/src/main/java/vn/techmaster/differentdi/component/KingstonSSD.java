package vn.techmaster.differentdi.component;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import vn.techmaster.differentdi.interfaces.ESata;

@Component("kingston")
@Order(2)
public class KingstonSSD implements ESata{
  
}

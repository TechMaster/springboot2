package vn.techmaster.differentdi.component;

import org.springframework.stereotype.Component;

import vn.techmaster.differentdi.interfaces.PowerSupply;

@Component("powersupply")
public class InverterPower implements PowerSupply{
  public InverterPower() {
    System.out.println("create powersupply");
  }
}

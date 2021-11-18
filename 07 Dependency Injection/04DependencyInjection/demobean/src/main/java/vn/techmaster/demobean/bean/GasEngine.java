package vn.techmaster.demobean.bean;

import vn.techmaster.demobean.interfaces.Engine;

public class GasEngine implements Engine {

  @Override
  public void start() {
    System.out.println("Gas Engine Start");    
  }

  @Override
  public void stop() {
    System.out.println("Gas Engine Stop");    
  }
  
}

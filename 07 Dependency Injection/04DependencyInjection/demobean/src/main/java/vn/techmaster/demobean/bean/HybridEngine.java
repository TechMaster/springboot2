package vn.techmaster.demobean.bean;

import vn.techmaster.demobean.interfaces.Engine;

public class HybridEngine implements Engine {

  @Override
  public void start() {
    System.out.println("Hybrid Engine Start");
  }

  @Override
  public void stop() {
    System.out.println("Hybrid Engine Stop");    
  }  
}

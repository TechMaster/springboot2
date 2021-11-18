package vn.techmaster.demobean.bean;

import vn.techmaster.demobean.interfaces.Engine;

public class ElectricEngine implements Engine {

  @Override
  public void start() {
    System.out.println("Electric Engine Start");    
  }

  @Override
  public void stop() {
    System.out.println("Electric Engine Stop");    
  }
  
}

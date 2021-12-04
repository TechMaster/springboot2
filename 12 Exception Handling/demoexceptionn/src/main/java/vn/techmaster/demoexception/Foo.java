package vn.techmaster.demoexception;

public class Foo implements Cloneable {
  @Override
  public Foo clone() throws CloneNotSupportedException {
    return (Foo) super.clone();
  }
}

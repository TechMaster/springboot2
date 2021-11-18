package foo;

public class Bar implements Swim{
  public void doSomething() {
    System.out.println("... program Java");
  }

  public void saySomething() {
    System.out.println("... hello world");
  }

  @Override
  public void swim() {
    System.out.println("... swim in ocean");    
  }
}

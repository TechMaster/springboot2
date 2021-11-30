package vn.techmaster.learncollection;

import java.util.List;
import java.util.function.Consumer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LambdaTest {
  
  @Test
  @DisplayName("No param lambda")
  public void no_param_lambda() {
    interface Sayable{  
      public void say();
    }
    Sayable sayable = () -> {  
      System.out.println("No param");
    };
    sayable.say();    
  }

  @Test
  @DisplayName("One param lambda")
  public void one_param_lambda() {
    interface OneParam{  
      public int execute(int x);
    }

    Consumer<int> multiple_by_2 = x -> {
      return x * 2;
    };

    OneParam square = x -> {
      return x * x;
    };

    OneParam abs = x -> {
      return x >= 0 ? x : -x;
    };

    multiple_by_2.execute(1);
  }

}

package vn.techmaster.demoexception;

import java.util.List;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class UncheckedExceptionTest {
  @Test
  public void unhandledNullPointerException() {
    // Không cần try catch vẫn biên dịch được, tuy nhiên nếu có exception sẽ crash
    List<String> list = null;
    list.add(1, "ABC");
  }

  @Test
  public void testNullPointerException() {
    try {
      List<String> list = null;
      list.add(1, "ABC");
    } catch (NullPointerException e) {
      System.out.println(e);
    }
  }

  @Test
  public void testArrayIndexOutOfBound() {
    assertThatThrownBy(() -> {
      List<String> list = List.of("a", "b", "c", "d");
      System.out.println(list.get(5));
    
    }).isInstanceOf(ArrayIndexOutOfBoundsException.class);
  }

  @Test
  public void testArithmeticException() {
    assertThatThrownBy(() -> {
      int x = 0;
      int y = 10;
      int z = y / x;
    }).isInstanceOf(ArithmeticException.class);


  }
}

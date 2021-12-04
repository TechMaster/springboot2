package vn.techmaster.demoexception;

import org.junit.jupiter.api.Test;

import vn.techmaster.demoexception.exception.FilmException;

public class CustomExceptionTest {
  @Test
  public void testFilmException() {
    try {
      foo();
    } catch (FilmException e) {
      System.out.println(e);
    }
  }

  public void foo() throws FilmException {
    System.out.println("Do something");
    throw new FilmException("Cannot find film", "video1.onemount.com", "ox-13", "0001");
  }
}

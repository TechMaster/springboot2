package vn.techmaster.autoconfigure.model;

import java.util.Random;

public class Film {
  public Film(String id, String title) {
    this.id = id;
    this.title = title;
  }

  public Film(String title) {
    this(generateId(5), title);
  }




  private static String generateId(int length) {
    int leftLimit = 48; // numeral '0'
    int rightLimit = 122; // letter 'z'
    Random random = new Random();

    return random.ints(leftLimit, rightLimit + 1)
      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
      .limit(length)
      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
      .toString();
  }
  
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  private String id;
  private String title;
}

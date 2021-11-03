package com.onemount.himaven.model;

import java.util.List;
import java.util.Random;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //Bổ xung Getter/Setter
@AllArgsConstructor
@NoArgsConstructor
public class Film {
  private String id;
  private String title;
  private String description;
  private List<String> actors;

  public Film(String title, String description, List<String> actors){
    this.id = generateId(4);
    this.title = title;
    this.description = description;
    this.actors = actors;
  }
  private String generateId(int length) {
    int leftLimit = 48; // numeral '0'
    int rightLimit = 122; // letter 'z'
    Random random = new Random();

    return random.ints(leftLimit, rightLimit + 1)
      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
      .limit(length)
      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
      .toString();
  }
}

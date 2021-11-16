package vn.techmasterr.bookstore.utils;

import com.github.javafaker.Faker;

public class GenerateId {
  public static final String generateUniqueId(int length) {
    Faker faker = new Faker();
    return faker.regexify("[a-zA-Z0-9]{" +  length + "}");
  }
}

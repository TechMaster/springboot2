package vn.techmaster.fluxh2reactive.model;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Person {
  private String name;
  private String email;
  public MultipartFile photo;
}
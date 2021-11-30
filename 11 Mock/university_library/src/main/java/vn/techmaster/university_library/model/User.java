package vn.techmaster.university_library.model;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
  private String id;
  private String name;
  private String email;
  private String mobile;
  private String hashpass;
  private Set<Book> rentedBooks;
}

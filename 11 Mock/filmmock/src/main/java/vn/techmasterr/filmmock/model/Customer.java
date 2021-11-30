package vn.techmasterr.filmmock.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
  private String id;
  private String name;
  private String email;
  private String mobile;
}

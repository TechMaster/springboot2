package vn.techmaster.defaultlog.model;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Film {
  private String id;
  private String title;
  private Set<String> genres;
}

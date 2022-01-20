package vn.techmaster.fluxh2reactive.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(value="post")
public class Post {
  @Id
  private Long id;
  private String title;
  private String content;
}

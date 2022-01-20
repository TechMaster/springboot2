package vn.techmaster.fluxh2reactive.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
Chú ý đây không phải là định nghĩa Entity bởi R2DBC không phải ORM như Hibernate
Spring Data R2DBC aims at being conceptually easy. 
In order to achieve this it does NOT offer caching, lazy loading, 
write behind or many other features of ORM frameworks. 
This makes Spring Data R2DBC a simple, limited, opinionated object mapper.
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(value="post")  //Với R2DBC chúng ta không dùng Entity tạo Object trong Hibernate như JPA
public class Post {
  @Id private Long id;
  private String title;
  private String content;
}

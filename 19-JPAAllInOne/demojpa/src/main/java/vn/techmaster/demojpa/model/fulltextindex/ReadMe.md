# Full Text Index
https://www.baeldung.com/hibernate-search
https://reflectoring.io/hibernate-search/

Hibernate 6
https://www.mindbowser.com/hibernate-search-6-with-spring-boot/

```xml
<dependency>
  <groupId>org.hibernate.search</groupId>
  <artifactId>hibernate-search-mapper-orm</artifactId>
  <version>6.0.7.Final</version>
</dependency>
<dependency>
  <groupId>org.hibernate.search</groupId>
  <artifactId>hibernate-search-backend-lucene</artifactId>
  <version>6.0.7.Final</version>
</dependency>
```

```java
@Entity(name = "post")
@Table(name = "post")
@Data
@Indexed
public class Post { 
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @FullTextField
    private String title;
}
```
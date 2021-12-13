package vn.techmaster.demomapstruct.dto.simple;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="person")
@Table(name="person")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
  @Id private long id;

  @Column(nullable = false, length = 40)
  private String firstname;

  @Column(nullable = false, length = 40)
  private String lastname;

  @NaturalId
  @Column(unique = true, nullable = false)
  private String email;

  @Column(nullable = false)
  private String password;
}

package vn.techmaster.demojpa.model.id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity(name="identityid")
@Table(name="identityid")
public class IdentityID {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
}

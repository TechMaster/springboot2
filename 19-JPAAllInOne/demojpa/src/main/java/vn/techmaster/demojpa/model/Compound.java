package vn.techmaster.demojpa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Data;

@Data
@Entity(name ="compound")
@Table(name ="compound")
public class Compound {
  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id1;
}

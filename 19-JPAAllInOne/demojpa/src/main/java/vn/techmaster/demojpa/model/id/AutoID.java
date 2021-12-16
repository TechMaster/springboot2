package vn.techmaster.demojpa.model.id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity(name="autoid")
@Table(name="autoid")
public class AutoID {
  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
}

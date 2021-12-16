package vn.techmaster.demojpa.model.id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity(name="sequenceid")
@Table(name="sequenceid")
public class SequenceID {
  @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;
}

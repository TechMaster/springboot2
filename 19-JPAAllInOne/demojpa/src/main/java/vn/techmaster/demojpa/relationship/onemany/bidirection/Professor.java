package vn.techmaster.demojpa.relationship.onemany.bidirection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PreRemove;
import javax.persistence.Table;



import lombok.Data;

@Entity @Table
@Data
public class Professor {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)

  private Long id;

  private String name;

  public Professor(String name) {
    this.name = name;
  }

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  //@JoinColumn(name="department_id")
  private Department department;

  @PreRemove
  public void preRemove() {
    department.remove(this);
  }
}

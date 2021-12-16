package vn.techmaster.demojpa.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;

@Entity(name ="oto")
@Table(name = "car")
@Data
@NamedQuery(name = "Car.findById", query = "SELECT c FROM oto c WHERE c.id=:id")
public class Car {
  @Id
  private long id;
  private String model;
  private String maker;
  private int year;  
}

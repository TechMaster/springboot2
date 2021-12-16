package vn.techmaster.demojpa.model.id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
//https://bushansirgur.in/spring-data-jpa-id-generators-with-examples/#Custom_generator
@Data
@Entity(name="bar")
@Table(name="bar")
public class Bar {
  @GenericGenerator(name = "random_id", strategy = "vn.techmaster.demojpa.model.id.RandomIDGenerator")
  @Id @GeneratedValue(generator="random_id")
  private String id;
  private String name;
}

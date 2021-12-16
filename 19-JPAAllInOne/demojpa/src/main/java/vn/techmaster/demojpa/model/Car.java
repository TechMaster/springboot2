package vn.techmaster.demojpa.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;

@Entity(name ="oto") //tên entity sẽ sử dụng trong câu lệnh JPQL
@Table(name = "car") //tên table sẽ sử dụng để lưu xuống bảng vật lý trong CSDL
@Data //annotation của Lombok
@NamedQuery(name = "Car.findById", query = "SELECT c FROM oto c WHERE c.id=:id")
public class Car {
  @Id
  private long id;
  private String model;
  private String maker;
  private int year;  
}
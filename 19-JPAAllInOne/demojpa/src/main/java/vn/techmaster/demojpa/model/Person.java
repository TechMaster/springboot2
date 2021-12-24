package vn.techmaster.demojpa.model;


import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "person")
@Table(name = "person")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {
  @Id  
  Long id;
  private String fullname;
  private String job;
  private String gender;
  private String city;
  private int salary;

  @Column(name="birthday")
  @Temporal(TemporalType.DATE)
  private Date birthday;

  @Column(name="sex")
  @Formula(value = "case when gender='Male' then true else false end")
  private Boolean sex;

  @Transient
  private int age;
  public int getAge(){
    Date safeDate = new Date(birthday.getTime());
    LocalDate birthDayInLocalDate = safeDate.toInstant()
    .atZone(ZoneId.systemDefault())
    .toLocalDate();
    return Period.between(birthDayInLocalDate, LocalDate.now()).getYears();
  }  
}
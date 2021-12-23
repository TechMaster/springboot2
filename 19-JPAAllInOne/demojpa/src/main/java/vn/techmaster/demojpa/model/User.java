package vn.techmaster.demojpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
@Entity(name="user")
@Table(name="users")
public class User {
  @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(name="fullname", nullable=false, length=50)
  private String name;

  @Pattern(regexp = "^(19|20)\\d\\d[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])$",
  message="Ngày sinh phải theo định dang yyyy-mm-dd")
  private String date;

  @Pattern(regexp = "^\\d{10,11}$", message = "Số di động phải có từ 10 đến 11 chữ số")
  private String mobile;

  @Pattern(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", 
  message="Email không hợp lệ")
  private String email;

  public User(String name, String date, String mobile, String email) {
    this.name = name;
    this.date = date;
    this.mobile = mobile;
    this.email = email;
  }
}

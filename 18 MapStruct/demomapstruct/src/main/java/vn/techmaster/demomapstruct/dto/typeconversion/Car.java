package vn.techmaster.demomapstruct.dto.typeconversion;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Car {
  private Long id;
  private String name;
  private int price;
  private Date manufactured_date;
}

package vn.techmaster.demomapstruct.dto.typeconversion;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CarDTO {
  private String id;
  private String name;
  private String price;
  private String manufactured_date;
}

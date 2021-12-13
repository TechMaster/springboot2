package vn.techmaster.demomapstruct.dto.bmi;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BMIImperial {
  private float height_inch;
  private float weight_pound;
}

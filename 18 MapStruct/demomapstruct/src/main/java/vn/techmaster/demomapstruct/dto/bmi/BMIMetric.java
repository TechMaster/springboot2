package vn.techmaster.demomapstruct.dto.bmi;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BMIMetric {
  private float height_cm;
  private float weight_kg;
}

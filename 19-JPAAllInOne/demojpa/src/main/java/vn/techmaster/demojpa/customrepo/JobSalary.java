package vn.techmaster.demojpa.customrepo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JobSalary {
  private String job;
  private double averagesalary;
}
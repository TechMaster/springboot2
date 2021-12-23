package vn.techmaster.demojpa.customrepo;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CityJobCount {
  private String city;
  private String job;
  private long count;
}

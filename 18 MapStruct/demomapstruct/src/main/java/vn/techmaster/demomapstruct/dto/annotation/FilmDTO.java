package vn.techmaster.demomapstruct.dto.annotation;

import java.util.Date;

import lombok.Data;

@Data
public class FilmDTO {
  private String name;
  private String director;
  private Date creationDate;
}

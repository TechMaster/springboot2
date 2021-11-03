package vn.techmasterr.netflux.model;

import java.util.List;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Film {
  private String id;
  private String name;
  private String description;
  private List<String> actors;

  public Film (String name, String description, List<String> actors) {
    this.id = NanoIdUtils.randomNanoId(NanoIdUtils.DEFAULT_NUMBER_GENERATOR, NanoIdUtils.DEFAULT_ALPHABET, 5);
    this.name = name;
    this.description = description;
    this.actors = actors;
  }
}

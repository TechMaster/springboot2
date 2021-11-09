package vn.techmasterr.netflux.model;

import java.util.List;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;

public record Film (String id, String name, String description, List<String> actors) {
  public Film(String name, String description, List<String> actors) {
    this(
      NanoIdUtils.randomNanoId(NanoIdUtils.DEFAULT_NUMBER_GENERATOR, NanoIdUtils.DEFAULT_ALPHABET, 5),
      name,
      description,
      actors);
  }
}

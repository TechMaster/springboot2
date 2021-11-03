package com.onemount.himaven.repo;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import com.onemount.himaven.model.Film;

@Repository
public class FilmRepo {
  private ArrayList<Film> films;

  public FilmRepo() {
    films = new ArrayList<>();
    films.add(new Film("Bố Già", "Tâm lý xã hội Việt nam", List.of("Trấn Thành", "Thương Tín")));
    films.add(new Film("Squid Game", "Netflix Series", List.of("Jang Dong Gun", "Lee Hories")));
  }

  public List<Film> findAll() {
    return films;
  }
}

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



  public String addNew(Film filmRequest) {
    Film film = new Film(filmRequest.getTitle(), filmRequest.getDescription(), filmRequest.getActors());
    films.add(film);
    return film.getId();
  }

  //Cập nhật film
  public String update(Film filmRequest) {
   for (Film film : films) {
     if(film.getId().equals(filmRequest.getId())) {
       film.setActors(filmRequest.getActors());
       film.setDescription(filmRequest.getDescription());
      film.setTitle(filmRequest.getTitle());
      return film.getId();
     }
   }
    return null;
  }
}

package vn.techmasterr.netflux.repo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import vn.techmasterr.netflux.model.Film;

@Repository
public class FilmRepo {
  private List<Film> films;
  public FilmRepo() {
    films = new ArrayList<>();
    films.add(new Film("Bố Già", "Phim Tâm Lý Việt", List.of("Trấn Thành", "Minh Tuấn")));
    films.add(new Film("God Father", "Italian Mafia Family in United States", List.of("Bazzilo", "Robert Deniro")));
  }
  public List<Film> getAll() {
    return films;
  }

  public Optional<Film> findById(String id) {
    return films.stream().filter(x -> x.id().equals(id)).findFirst();
  }

  public String create(Film filmRequest) {
    var film  = new Film(filmRequest.name(), filmRequest.description(), filmRequest.actors());
    films.add(film);
    return film.id();
  }

  public String upsert(Film filmRequest) {
    int index = 0;
    boolean found = false;
    for (var film : films) {
      if (film.id().equals(filmRequest.id())) {
        found = true;
        break;
      }
      index += 1;
    }
    if (found) {
      films.set(index, filmRequest);  //update
    } else {  //Create new
      films.add(filmRequest);
    }

    return filmRequest.id();
  }
}

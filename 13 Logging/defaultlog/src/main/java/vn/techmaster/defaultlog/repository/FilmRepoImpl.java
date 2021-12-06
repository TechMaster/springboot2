package vn.techmaster.defaultlog.repository;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import vn.techmaster.defaultlog.dto.FilmRequest;
import vn.techmaster.defaultlog.exception.FilmException;
import vn.techmaster.defaultlog.model.Film;

@Repository
public class FilmRepoImpl implements FilmRepo {
  private ConcurrentHashMap<String, Film> films;
  public FilmRepoImpl() {
    films = new ConcurrentHashMap<>();
    films.put("001", new Film("001", "Squid Games", Set.of("series", "horror", "korean")));
    films.put("002", new Film("002", "World War 2 in colors", Set.of("series", "documentary", "history")));
    films.put("003", new Film("003", "Bố Già", Set.of("drama", "love")));
  }

  @Override
  public Optional<Film> getFilmById(String id) {
    return Optional.ofNullable(films.get(id));
  }

  @Override
  public void add(FilmRequest filmRequest) {
    String id = filmRequest.id();
    if (films.containsKey(id)) {
      throw new FilmException("Film with id already exists", id);
    }
    films.put(id, 
    new Film(id, filmRequest.title(), filmRequest.genres()));

  }
  
}

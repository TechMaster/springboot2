package vn.techmaster.demoexception.repository;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import vn.techmaster.demoexception.model.Film;

@Repository
public class FilmRepoImpl implements FilmRepo {
  private ConcurrentHashMap<String, Film> films;

  public FilmRepoImpl() {
    films = new ConcurrentHashMap<>();
    films.put("001", new Film("001", "Squid Game"));
    films.put("002", new Film("002", "Gone with the wind"));
    films.put("003", new Film("003", "Money Heist"));
  }
  
  @Override
  public Optional<Film> findFilmById(String id) {
    return Optional.ofNullable(films.get(id));
  }
}

package vn.techmasterr.filmmock.repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import vn.techmasterr.filmmock.model.Film;

@Repository
public class FilmRepoImpl implements FilmRepo {
  private ConcurrentHashMap<String, Film> films;

  public FilmRepoImpl() {
    films = new ConcurrentHashMap<>();
    films.put("001", new Film("001", "Squid Games"));
    films.put("002", new Film("002", "Money Heist"));
    films.put("003", new Film("003", "Bố Già"));
    films.put("004", new Film("004", "Million Dollars Code"));

  }
  @Override
  public List<Film> getAllFilms() {
    return films.values().stream().toList();
  }

  @Override
  public Optional<Film> findById(String id) {    
    return Optional.of(films.get(id));
  }
  
}

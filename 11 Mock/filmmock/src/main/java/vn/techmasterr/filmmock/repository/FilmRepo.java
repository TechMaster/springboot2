package vn.techmasterr.filmmock.repository;

import java.util.List;
import java.util.Optional;

import vn.techmasterr.filmmock.model.Film;

public interface FilmRepo {
  public List<Film> getAllFilms();
  public Optional<Film> findById(String id);
}

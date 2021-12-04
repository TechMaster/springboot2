package vn.techmaster.demoexception.repository;

import java.util.Optional;

import vn.techmaster.demoexception.model.Film;

public interface FilmRepo {
  public Optional<Film> findFilmById(String id);
}

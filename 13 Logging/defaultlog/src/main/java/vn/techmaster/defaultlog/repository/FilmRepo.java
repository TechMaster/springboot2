package vn.techmaster.defaultlog.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import vn.techmaster.defaultlog.dto.FilmRequest;
import vn.techmaster.defaultlog.model.Film;

@Repository
public interface FilmRepo {
  Optional<Film> getFilmById(String id);

  void add(FilmRequest filmRequest);
}

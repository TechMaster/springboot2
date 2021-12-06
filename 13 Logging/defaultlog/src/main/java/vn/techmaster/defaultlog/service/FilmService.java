package vn.techmaster.defaultlog.service;

import vn.techmaster.defaultlog.dto.FilmRequest;
import vn.techmaster.defaultlog.model.Film;

public interface FilmService {
  Film getFilmById(String id);
  void add(FilmRequest filmRequest);
}

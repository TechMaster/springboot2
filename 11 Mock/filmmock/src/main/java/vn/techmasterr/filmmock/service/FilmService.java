package vn.techmasterr.filmmock.service;

import vn.techmasterr.filmmock.exception.FilmException;
import vn.techmasterr.filmmock.model.Film;

public interface FilmService {
  Film findByTitle(String title) throws FilmException;
  void rentAFilm(String customerId, String filmId) throws FilmException;
}

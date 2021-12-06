package vn.techmaster.defaultlog.service;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import vn.techmaster.defaultlog.dto.FilmRequest;
import vn.techmaster.defaultlog.exception.FilmException;
import vn.techmaster.defaultlog.model.Film;
import vn.techmaster.defaultlog.repository.FilmRepo;

@Service
@Slf4j
public class FilmServiceImpl implements FilmService {
  private FilmRepo filmRepo;
  public FilmServiceImpl(FilmRepo filmRepo) {
    this.filmRepo = filmRepo;
  }

  @Override
  public Film getFilmById(String id) {
    log.trace("getFilmById(" + id + ")");  
    return filmRepo.getFilmById(id)
    .orElseThrow(() -> {
      return new FilmException("Film not found", id);
    });
  }

  @Override
  public void add(FilmRequest filmRequest) {
    filmRepo.add(filmRequest);    
  }
  
}

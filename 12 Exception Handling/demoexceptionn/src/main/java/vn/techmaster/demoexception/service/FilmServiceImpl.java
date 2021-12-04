package vn.techmaster.demoexception.service;

import org.springframework.stereotype.Service;

import vn.techmaster.demoexception.model.Film;
import vn.techmaster.demoexception.repository.FilmRepo;

@Service
public class FilmServiceImpl implements FilmService {
  private FilmRepo filmRepo;
  public FilmServiceImpl(FilmRepo filmRepo) {
    this.filmRepo = filmRepo;
  }
  @Override
  public Film findFilmById(String id) {
    return filmRepo.findFilmById(id)
    .orElseThrow((() -> new RuntimeException("Film not found")));
  }
  
}

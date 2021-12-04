package vn.techmaster.demoexception.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import vn.techmaster.demoexception.model.Film;
import vn.techmaster.demoexception.service.FilmService;

@RestController
public class FilmController {
  private FilmService filmService;

  public FilmController(FilmService filmService) {
    this.filmService = filmService;
  }

  @GetMapping("/film/{id}")
  public ResponseEntity<Film> getFilmById(@PathVariable String id) {
    return ResponseEntity.ok(filmService.findFilmById(id));
  }
}

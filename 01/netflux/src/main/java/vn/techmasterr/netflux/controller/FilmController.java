package vn.techmasterr.netflux.controller;

import org.springframework.web.bind.annotation.RestController;

import vn.techmasterr.netflux.repo.FilmRepo;
import vn.techmasterr.netflux.model.Film;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
public class FilmController {
  @Autowired private FilmRepo filmRepo;

  @GetMapping(value="/api/films")
  public List<Film> getFilms() {
      return filmRepo.getAll();
  }

  @PostMapping(value="/api/film")
  @ResponseStatus(HttpStatus.CREATED)
  public String createNewFilm(@RequestBody Film filmRequest) {      
      return filmRepo.create(filmRequest);
  }

  @PutMapping(value="/api/film")
  @ResponseStatus(HttpStatus.OK)
  public String updateFilm(@RequestBody Film filmRequest) {      
      return filmRepo.upsert(filmRequest);
  }  
}
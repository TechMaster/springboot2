package com.onemount.himaven.controller;

import java.util.List;

import javax.annotation.Resource;

import com.onemount.himaven.model.Film;
import com.onemount.himaven.repo.FilmRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
@RestController
public class FilmController {
  @Autowired private FilmRepo filmRepo;
  
  @GetMapping(value="/api/films")
  public List<Film> getAll() {
    return filmRepo.findAll();
  }

  @PostMapping(value="/api/film")
  @ResponseStatus(HttpStatus.CREATED)
  public String addNewFilm(@RequestBody Film filmRequest) {
    return filmRepo.addNew(filmRequest);
  }

  @PutMapping(value="/api/film")
  @ResponseStatus(HttpStatus.OK)
  public String updateFilm(@RequestBody Film filmRequest) {
    return filmRepo.update(filmRequest);
  }

}

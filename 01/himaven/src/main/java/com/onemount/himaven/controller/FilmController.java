package com.onemount.himaven.controller;

import java.util.List;

import com.onemount.himaven.model.Film;
import com.onemount.himaven.repo.FilmRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilmController {
  @Autowired private FilmRepo filmRepo;
  
  @GetMapping(value="/api/films")
  public List<Film> getAll() {
    return filmRepo.findAll();
  }

  @PostMapping(value="/api/film")
  public String addNewFilm(Film filmRequest) {
    return filmRepo.
  }

}

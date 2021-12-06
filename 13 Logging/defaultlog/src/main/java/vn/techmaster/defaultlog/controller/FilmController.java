package vn.techmaster.defaultlog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.techmaster.defaultlog.dto.FilmRequest;
import vn.techmaster.defaultlog.model.Film;
import vn.techmaster.defaultlog.service.FilmService;

@RestController
@RequestMapping("/film")
public class FilmController {
  private FilmService filmService; 
  public FilmController(FilmService filmService) {
    this.filmService = filmService;
  }
  
  // Sử dụng log object được tạo ra từ annotation @Slf4j
  @GetMapping("/{id}")
  public Film getFilmById(@PathVariable String id) {
    return filmService.getFilmById(id);
  }

  @PostMapping()
  public String addNewFilm (@RequestBody FilmRequest filmRequest) {
    filmService.add(filmRequest);
    return "Film adds successfully";
  }
}

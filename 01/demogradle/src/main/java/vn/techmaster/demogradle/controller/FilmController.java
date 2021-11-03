package vn.techmaster.demogradle.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.techmaster.demogradle.model.Film;

import java.util.List;


@RestController
public class FilmController {
	@GetMapping("/api/films")
	private List<Film> getAllFilms() {
		return List.of(new Film("001", "Bố Già", "Film Việt nam"),
				new Film("002", "GodZilla", "Japanese Scifi"));
	}
}

package vn.techmaster.imdb;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import vn.techmaster.imdb.model.Film;
import vn.techmaster.imdb.repository.FilmRepository;

@SpringBootTest
class FilmRepoTest {
	@Autowired private FilmRepository filmRepo;

	@Test
	public void getAll() {
		List<Film> filmList = filmRepo.getAll();
	}

}

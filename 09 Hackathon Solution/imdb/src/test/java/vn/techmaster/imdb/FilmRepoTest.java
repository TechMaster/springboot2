package vn.techmaster.imdb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import vn.techmaster.imdb.model.Film;
import vn.techmaster.imdb.repository.FilmRepository;

class FilmRepoTest {
	
	@Test
	public void getFilmByCountry() {
		FilmRepository filmRepo = new FilmRepository("film1.json");
		Map<String, List<Film>> result =  filmRepo.getFilmByCountry();

		// Đoạn này để debug
		result.entrySet().forEach(x -> {
			System.out.println(x.getKey()); //Country
			x.getValue().forEach(film -> {
				System.out.println("  " + film.getTitle());
			});
		});
		
		assertThat(result.keySet()).containsExactlyInAnyOrder("Malta", "Netherlands", "China", "Portugal");	
	}

	@Test
	/*
	Hàm này dùng để thử nghiệm, chạy ok thì mới cho vào Repo !
	*/
	public void prototype_getcountryMakeMostFilms() {
		FilmRepository filmRepo = new FilmRepository("filmsmall.json");
		List<Film> films = filmRepo.getAll();
		Map.Entry<String, Long> result = films.stream()
		.collect(Collectors.groupingBy(Film::getCountry, Collectors.counting()))
		.entrySet().stream()
		.max(Comparator.comparing(Map.Entry<String, Long>::getValue)).get();
		
		assertThat(result.getKey()).isEqualTo("China");
		assertThat(result.getValue()).isEqualTo(9);
	}

	@Test
	public void getcountryMakeMostFilms() {
		FilmRepository filmRepo = new FilmRepository("filmsmall.json");
		Map.Entry<String, Long>  result = filmRepo.getcountryMakeMostFilms();	
		assertThat(result.getKey()).isEqualTo("China");
		assertThat(result.getValue()).isEqualTo(9);
	}

	@Test
	public void yearMakeMostFilms() {
		FilmRepository filmRepo = new FilmRepository("filmsmall.json");
		Map.Entry<Integer, Long>  result = filmRepo.yearMakeMostFilms();
		assertThat(result.getKey()).isEqualTo(1985);
		assertThat(result.getValue()).isEqualTo(4);
	}

	@Test
	public void yearMakeMostFilms2() {
		FilmRepository filmRepo = new FilmRepository("film2.json");
		Map.Entry<Integer, Long>  result = filmRepo.yearMakeMostFilms();
		assertThat(result.getKey()).isEqualTo(1985);
		assertThat(result.getValue()).isEqualTo(2);
	}

	@Test
	/*
	Đoạn này để thử nghiệm, mình có thể viết bao nhiêu unit test cũng được do đó cứ thoải mái
	*/
	public void prototype_getallgenres() {
		FilmRepository filmRepo = new FilmRepository("film1.json");
		List<Film> films = filmRepo.getAll();
		Set<String> genres = films.stream().map(Film::getGeneres)
		.flatMap(Collection::stream)  //hoặc .flatMap(x -> x.stream())
		.collect(Collectors.toSet());
		genres.forEach(System.out::println);

		System.out.println("----");
		Set<String> genres2 = new HashSet<>();
		for (Film film : films) {
			genres2.addAll(film.getGeneres());
		}		
		genres2.forEach(System.out::println);
	}

	@Test
	public void getallgenres() {
		FilmRepository filmRepo = new FilmRepository("film1.json");
		Set<String> genres = filmRepo.getAllGeneres();

		Set<String> genres2 = new HashSet<>();
		for (Film film : filmRepo.getAll()) {
			genres2.addAll(film.getGeneres());
		}		
		assertThat(genres).containsExactlyElementsOf(genres2);
	}

	@ParameterizedTest
	@CsvSource({"Japan, 1965, 2005",	"France, 2004, 2021"})
	public void getFilmsMadeByCountryFromYearToYear(String country, int fromYear, int toYear) {
		FilmRepository filmRepo = new FilmRepository("film.json");

		List<Film> actual_filmCountryYear2Year = filmRepo.getFilmsMadeByCountryFromYearToYear(country, fromYear, toYear);
		
		List<Film> expected_filmCountryYear2Year = new ArrayList<>();

		for (Film film : filmRepo.getAll()) {
			if (film.getCountry().equalsIgnoreCase(country) && film.getYear() >= fromYear && film.getYear() <= toYear){
				expected_filmCountryYear2Year.add(film);
			}
		}

		assertThat(actual_filmCountryYear2Year).containsExactlyInAnyOrderElementsOf(expected_filmCountryYear2Year);

	}

	@Test
	//Cách 1: của những lập trình viên căn bản vẫn hay làm
  public void prototype_categorizeFilmByGenere() {
    FilmRepository filmRepo = new FilmRepository("filmsmall.json");
		List<Film> films = filmRepo.getAll();

		Set<String> genres = filmRepo.getAllGeneres();
		Map<String, List<Film>> filmsByGenere = new HashMap<>();
		genres.forEach(genre -> {
			filmsByGenere.put(genre,  films.stream().filter(f -> f.getGeneres().contains(genre)).toList());
		});

		//Phần này chỉ in ra console
		filmsByGenere.entrySet().forEach(
			entry -> {
				System.out.println(entry.getKey());
				entry.getValue().forEach(film -> {
					System.out.println("  " + film);
				});
			}
		);

		//Cách cổ điển
		for (var entry : filmsByGenere.entrySet()) {
			System.out.println(entry.getKey());
			for (var film : entry.getValue()) {
				System.out.println("  " + film);
			}
		}
  }

	/*
	Lấy tất cả các film ứng với một thể loại genere
	*/
	public List<Film> listAllFilmContainsGenere(String genere) {
		FilmRepository filmRepo = new FilmRepository("filmsmall.json");
		List<Film> films = filmRepo.getAll();

		return films.stream()
		.filter(f -> f.getGeneres().contains(genere))
		.toList();
	}

	@Test
	/*
	Duỵệt qua từng film, lấy từng genere trong trường genres rồi thêm film vào filmsByGenere
	*/
	public void prototype_categorizeFilmByGenere2() {
		FilmRepository filmRepo = new FilmRepository("filmsmall.json");
		List<Film> films = filmRepo.getAll();
		Map<String, List<Film>> filmsByGenere = new HashMap<>();
		
		films.forEach(f -> {
			f.getGeneres().forEach(genere -> {
				if (filmsByGenere.get(genere) == null) {
					filmsByGenere.put(genere, new ArrayList<>(List.of(f)));				
				} else {
					filmsByGenere.get(genere).add(f);
				}
			});
		});

		for (var entry : filmsByGenere.entrySet()) {
			System.out.println(entry.getKey());
			for (var film : entry.getValue()) {
				System.out.println("  " + film);
			}
		}
	}



}

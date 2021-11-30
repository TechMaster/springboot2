package vn.techmaster.imdb.repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import vn.techmaster.imdb.model.Film;

@Repository
public class FilmRepository implements IFilmRepo{
  private List<Film> films;

  @Autowired //Vì FilmRepository có 2 constructor nên phải chọn một constructor gắn annotation @Autowired
  public FilmRepository(@Value("${datafile}") String datafile) {
    try {
      File file = ResourceUtils.getFile("classpath:static/" + datafile);
      ObjectMapper mapper = new ObjectMapper(); // Dùng để ánh xạ cột trong CSV với từng trường trong POJO
      films = Arrays.asList(mapper.readValue(file, Film[].class));
    } catch (IOException e) {
      System.out.println(e);
    }
  }

  public FilmRepository(List<Film> films) {
    this.films = films;
  }

  public List<Film> getAll() {
    return films;
  }

  @Override
  public Map<String, List<Film>> getFilmByCountry() {
    return films.stream().collect(Collectors.groupingBy(Film::getCountry));
  }

  @Override
  public Map.Entry<String, Long> getcountryMakeMostFilms() {
    return films.stream()
		.collect(Collectors.groupingBy(Film::getCountry, Collectors.counting()))
    .entrySet().stream()
		.max(Comparator.comparing(Map.Entry<String, Long>::getValue)).get();
  }

  @Override
  public Map.Entry<Integer, Long> yearMakeMostFilms() {
    return films.stream()
		.collect(Collectors.groupingBy(Film::getYear, Collectors.counting()))
    .entrySet().stream()
		.max(Comparator.comparing(Map.Entry<Integer, Long>::getValue)).get();
  }

  @Override
  public Set<String> getAllGeneres() {
    return films.stream().map(Film::getGeneres)
		.flatMap(x -> x.stream())  //hoặc .flatMap(x -> x.stream())
		.collect(Collectors.toSet());
  }

  @Override
  public List<Film> getFilmsMadeByCountryFromYearToYear(String country, int fromYear, int toYear) {    
    return films.stream().filter(f -> f.getCountry().equalsIgnoreCase(country) 
    && f.getYear() >= fromYear 
    && f.getYear() <= toYear).collect(Collectors.toList());
  } 



  @Override
  public List<Film> top5HighMarginFilms() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Film> top5HighMarginFilmsIn1990to2000() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public double ratioBetweenGenere(String genreX, String genreY) {
    long countX = films.stream().filter(f -> f.getGeneres().contains(genreX)).count();
    long countY = films.stream().filter(f -> f.getGeneres().contains(genreY)).count();
    return (double) countX/countY;
  }

  @Override
  public List<Film> top5FilmsHighRatingButLowMargin() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Map<String, List<Film>> categorizeFilmByGenere() {
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

    return filmsByGenere;
    
  }

}

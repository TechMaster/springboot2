package vn.techmaster.imdb.repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import vn.techmaster.imdb.model.Film;

@Repository
public class FilmRepository implements IFilmRepo{
  private List<Film> films;

  public FilmRepository(@Value("${datafile}") String datafile) {
    try {
      File file = ResourceUtils.getFile("classpath:static/" + datafile);
      ObjectMapper mapper = new ObjectMapper(); // Dùng để ánh xạ cột trong CSV với từng trường trong POJO
      films = Arrays.asList(mapper.readValue(file, Film[].class));
    } catch (IOException e) {
      System.out.println(e);
    }
  }

  public List<Film> getAll() {
    return films;
  }

  @Override
  public Map<String, List<Film>> getFilmByCountry() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Entry<String, Integer> getcountryMakeMostFilms() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Entry<Integer, Integer> yearMakeMostFilms() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<String> getAllGeneres() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Film> getFilmsMadeByCountryFromYearToYear(String country, int fromYear, int toYear) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Map<String, List<Film>> categorizeFilmByGenere() {
    // TODO Auto-generated method stub
    return null;
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
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public List<Film> top5FilmsHighRatingButLowMargin() {
    // TODO Auto-generated method stub
    return null;
  }

}

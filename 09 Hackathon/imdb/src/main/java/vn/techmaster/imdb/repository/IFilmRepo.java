package vn.techmaster.imdb.repository;

import java.util.List;
import java.util.Map;


import vn.techmaster.imdb.model.Film;

public interface IFilmRepo {
  //Phân loại danh sách film theo quốc gia sản xuất
  public Map<String, List<Film>> getFilmByCountry();

  //Tìm film do một quốc gia sản xuất từ năm X đến năm Y
  public List<Film> getFilmsMadeByCountryFromYearToYear(String country, int fromYear, int toYear);

  //Nước nào sản xuất nhiều film nhất, số lượng bao nhiêu?
  public Map.Entry<String, Integer> getcountryMakeMostFilms();

  //Năm nào sản xuất nhiều film nhất, số lượng bao nhiêu?
  public Map.Entry<Integer, Integer> yearMakeMostFilms();

  //Danh sách tất cả thể loại film
  public List<String> getAllGeneres();

  //Phân loại film theo thể loại
  public Map<String, List<Film>> categorizeFilmByGenere();


  //Top 5 film có lãi lớn nhất margin = revenue - cost
  public List<Film> top5HighMarginFilms();

  //Top 5 film từ năm 1990 đến 2000 có lãi lớn nhất
  public List<Film> top5HighMarginFilmsIn1990to2000();

  //Tỷ lệ phim giữa 2 thể loại
  public double ratioBetweenGenere(String genreX, String genreY);

  //Top 5 film có rating cao nhất nhưng lãi thì thấp nhất (thậm chí lỗ)
  public List<Film> top5FilmsHighRatingButLowMargin();
}

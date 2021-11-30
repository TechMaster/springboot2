# Hackathon số 1: lập trình Java Stream, JUnit5, AssertJ

Yêu cầu: Hãy lập trình implement 10 phương thức dưới đây.
Sau đó viết Unit Test để verify.

Thời gian bắt đầu làm bài: 24/10/2021 6:00PM
Thời gian nộp bài 25/10/2021 11:00 AM

**Cách tính điểm:**
1. Viết đúng phương thức được 0.5 điểm cho mỗi phương thức.
2. Viết hàm Unit Test hợp lý được 0.5 điểm.
3. Nộp muộn sau 11:00 AM nhận 0 điểm, do đó làm được bao nhiêu câu cứ nộp.
4. Quay cóp 0 điểm. Báo cáo với công ty chủ quản.

**Hướng dẫn**

```
.
├── main
│   ├── java
│   │   └── vn
│   │       └── techmaster
│   │           └── imdb
│   │               ├── model
│   │               │   └── Film.java 
│   │               ├── repository
│   │               │   ├── FilmRepository.java <-- Lập trình các phương thức ở đây
│   │               │   └── IFilmRepo.java
│   │               └── ImdbApplication.java
│   ├── resources
│   │   ├── static
│   │   │   ├── film.json <-- 1000 bản ghi
│   │   │   └── filmsmall.json <-- 30 bản ghi dễ kiểm tra lại bằng tay
│   │   ├── templates
│   │   └── application.properties <-- chuyển đổi giữa film.json và filmsmall.json
├── test
│   └── java
│       └── vn
│           └── techmaster
│               └── imdb
│                   └── FilmRepoTest.java <-- Viết unit test ở đây

```

```java
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
```
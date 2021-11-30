package vn.techmaster.imdb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import vn.techmaster.imdb.model.Film;
import vn.techmaster.imdb.repository.FilmRepository;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = { "-Xms2G", "-Xmx2G" })
public class BenchmarkFilm {
  private static FilmRepository filmRepo;
  private static List<Film> films;

  @Setup
  public void setup() {
    filmRepo = new FilmRepository("film.json");
    films = filmRepo.getAll();
  }

  public static void main(String[] args) throws InterruptedException, RunnerException {
    Options opt = new OptionsBuilder().include(BenchmarkFilm.class.getSimpleName()).forks(1).build();
    new Runner(opt).run();
  }

  @Benchmark
  @BenchmarkMode(Mode.AverageTime)
  public void groupFilmByGenere1() {
    Set<String> genres = filmRepo.getAllGeneres();
    Map<String, List<Film>> filmsByGenere = new HashMap<>();

    genres.forEach(genre -> {
      filmsByGenere.put(genre, films.stream().filter(f -> f.getGeneres().contains(genre)).toList());
    });
  }

  @Benchmark
  @BenchmarkMode(Mode.AverageTime)
  public void groupFilmByGenere2() {
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
  }

}

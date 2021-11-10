package vn.techmaster.hiaop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.techmaster.hiaop.annotation.Benchmark;
import vn.techmaster.hiaop.annotation.ReturnBody;
import vn.techmaster.hiaop.model.Film;

@Service
public class FilmService {
  public void create() {
    System.out.println("create new film");
  }

  @Benchmark
  public void stream() {
    System.out.println("Start streaming");
    try {
      Thread.sleep(500);
    } catch (InterruptedException ie) {
      Thread.currentThread().interrupt();
    }
    System.out.println("End streaming");
  }

  @ReturnBody(value="XML")
  public List<Film> findAll() {
    return List.of(
      new Film("Bố Già", "Trấn Thành"),
      new Film("Titanic", "James Cameron")
    );
  }
}

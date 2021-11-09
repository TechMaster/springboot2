package vn.techmaster.autoconfigure.repository;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Repository;

import vn.techmaster.autoconfigure.event.CustomEvent;
import vn.techmaster.autoconfigure.model.Film;

@Repository
public class FilmRepo implements ApplicationListener<CustomEvent>{
  private ConcurrentHashMap<String, Film> films;
  public FilmRepo() {
    films = new ConcurrentHashMap<>();
  }

  public String addNew(String title) {
    Film film = new Film(title);
    films.put(film.getId(), film);
    return film.getId();
  }

  @Cacheable(value="film", key="#id")
  public Optional<Film> findById(String id) {
    return Optional.ofNullable(films.get(id));
  }

  @Cacheable(value="films")
  public List<Film> getAll() {
    return films.values().stream().toList();
  }

  @Override
  public void onApplicationEvent(CustomEvent event) {
    System.out.println(event);    
  }


}

package vn.techmaster.fluxh2reactive.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import reactor.core.publisher.Flux;
import vn.techmaster.fluxh2reactive.model.Post;

public interface PostRepo extends ReactiveCrudRepository<Post, Long>{
  @Query("SELECT * FROM post WHERE title like $1")
  Flux<Post> findByTitleContains(String name);
}

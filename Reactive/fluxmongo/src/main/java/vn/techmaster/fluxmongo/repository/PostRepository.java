package vn.techmaster.fluxmongo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import vn.techmaster.fluxmongo.exception.PostNotFoundException;
import vn.techmaster.fluxmongo.model.Post;

@Repository
@Slf4j
public class PostRepository {
  private static final List<Post> DATA = new ArrayList<>();

  static {
    DATA.add(Post.builder().id(UUID.randomUUID()).title("post one").content("content of post one").build());
    DATA.add(Post.builder().id(UUID.randomUUID()).title("post two").content("content of post two").build());
  }

  public Flux<Post> findAll() {
    return Flux.fromIterable(DATA);
  }

  public Mono<Post> findById(UUID id) {
    return findAll().filter(p -> p.getId().equals(id)).last()
    .doOnError(throwable -> log.error("Failed for some reason", new PostNotFoundException(id)));
  }

  public Mono<Post> save(Post post) {
    Post saved = Post.builder().id(UUID.randomUUID()).title(post.getTitle()).content(post.getContent()).build();
    log.debug("saved post: {}", saved);
    DATA.add(saved);
    return Mono.just(saved);
  }

  
}

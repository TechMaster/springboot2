package vn.techmaster.fluxh2.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import vn.techmaster.fluxh2.exception.PostNotFoundException;
import vn.techmaster.fluxh2.model.Post;
import vn.techmaster.fluxh2.repository.PostRepo;

@RestController
@RequestMapping(value = "/posts")
public class PostController {
  private PostRepo postRepo;

  public PostController(PostRepo postRepo) {
    this.postRepo = postRepo;
  }

  // https://github.com/hantsy/spring-webmvc-functional-sample/blob/master/java/src/main/java/com/example/demo/DemoApplication.java
  @GetMapping(value = "")
  public Flux<Post> all() {
    return Flux.fromIterable(postRepo.findAll());
  }

  @GetMapping(value = "/{id}")
  public Mono<Post> get(@PathVariable(value = "id") Long id) {
    return postRepo.findById(id)
        .map(post -> Mono.just(post))
        .orElse(Mono.error(new PostNotFoundException(id)));
  }

  @PostMapping()
  public Mono<Post> create(@RequestBody Post post) {
    return Mono.just(postRepo.save(post));
  }

  @DeleteMapping(value = "/{id}")
  public Mono<Void> delete(@PathVariable(value = "id") Long id) {
    return Mono.fromCallable(() -> {
      postRepo.deleteById(id);
      return null;
    });
  }

  @PutMapping(value = "/{id}")
  public Mono<Post> update(@PathVariable("id") Long id, @RequestBody Post post) {
    return postRepo.findById(id)
        .map(oldpost -> Mono.just(oldpost))
        .orElse(Mono.error(new PostNotFoundException(id)))
        .map(p -> {
          p.setTitle(post.getTitle());
          p.setContent(post.getContent());
          return p;
        })
        .flatMap(x -> Mono.just(postRepo.save(x)));

  }

}

package vn.techmaster.fluxmongo.controller;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import vn.techmaster.fluxmongo.exception.PostNotFoundException;
import vn.techmaster.fluxmongo.model.Post;
import vn.techmaster.fluxmongo.repository.PostRepository;

@RestController
@RequestMapping(value = "/posts")
class PostController {

    private final PostRepository postRepo;

    public PostController(PostRepository postRepo) {
        this.postRepo = postRepo;
    }

    @GetMapping(value = "")
    public Flux<Post> all() {
        return this.postRepo.findAll();
    }

    @GetMapping(value = "/{id}")
    public Mono<Post> get(@PathVariable(value = "id") UUID id) {
        return this.postRepo.findById(id);
        //.switchIfEmpty(Mono.error(new PostNotFoundException(id)));
    }

    @PostMapping(value = "")
    public Mono<ResponseEntity<?>> create(@RequestBody Post post) {
        return this.postRepo.save(post)
        .map(p -> ResponseEntity.created(URI.create("/posts/" + p.getId())).build());
    }

    /*@DeleteMapping(value = "/{id}")
    public Mono<String> delete(@PathVariable(value = "id") UUID id) {

    }*/

}

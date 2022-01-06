package vn.techmaster.bookstorenetty.controller;


import com.github.javafaker.Faker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import vn.techmaster.bookstorenetty.model.Book;


@RestController
@RequestMapping("/api")
public class BookController {

  @GetMapping("/randombook")
  public Mono<Book> getRandomBook() throws InterruptedException {
    Thread.sleep(2000);  //Giả lập trễ khi gọi
    Faker faker = new Faker();    
    return Mono.just(new Book(faker.book().title(), faker.book().author()));
  }

}

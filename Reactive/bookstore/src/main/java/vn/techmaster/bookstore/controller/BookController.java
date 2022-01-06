package vn.techmaster.bookstore.controller;


import com.github.javafaker.Faker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.techmaster.bookstore.model.Book;


@RestController
@RequestMapping("/api")
public class BookController {

  @GetMapping("/randombook")
  public Book getRandomBook() throws InterruptedException {
    Thread.sleep(2000);  //Giả lập trễ khi gọi
    Faker faker = new Faker();    
    return new Book(faker.book().title(), faker.book().author());
  }

}

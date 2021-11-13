package vn.techmaster.bookinventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.techmaster.bookinventory.repository.BookRepo;

@RestController
public class BookController {
  @Autowired private BookRepo bookRepository;
  @GetMapping("/add")
  public String addBook() {
    bookRepository.demo();
    return "OK";
  }
}

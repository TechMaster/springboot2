package vn.techmasterr.bookstore.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import vn.techmasterr.bookstore.service.BookService;

@Component
public class AppRunner implements CommandLineRunner {
  @Autowired private BookService bookService;
  @Override
  public void run(String... args) throws Exception {
    bookService.Create1000Books();    
  }
  
}

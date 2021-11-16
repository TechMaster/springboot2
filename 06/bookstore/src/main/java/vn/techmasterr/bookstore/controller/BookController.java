package vn.techmasterr.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.techmasterr.bookstore.dto.BuyBook;
import vn.techmasterr.bookstore.exception.BookException;
import vn.techmasterr.bookstore.model.Book;
import vn.techmasterr.bookstore.model.BookInventory;
import vn.techmasterr.bookstore.repository.BookInventoryRepo;
import vn.techmasterr.bookstore.repository.BookRepo;
import vn.techmasterr.bookstore.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {
  

  @Autowired
  BookService bookService;


      
      @PostMapping("/buy")
      public void buyBook(@RequestBody BuyBook buyBook) throws Exception{
          bookService.buyBook(buyBook);
      }

      @GetMapping()
      public List<Book> getAllBook(){
        return bookRepo.getAllBook();
      }
  }



package vn.techmasterr.bookstore.repository;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import vn.techmasterr.bookstore.dto.NewBook;
import vn.techmasterr.bookstore.model.Book;

@Repository
public class BookRepo {
  private ConcurrentHashMap<String, Book> books;
  
  public BookRepo() {
    books = new ConcurrentHashMap<>();
  }
  public Book createNewBook(NewBook newBook) {
    // TODO:
    return null;
  }

  public List<Book> getAllSortByTitle() {
    return null;
  }

  public List<Book> searchByTitle(String keyword) {
    return null;
  }
}

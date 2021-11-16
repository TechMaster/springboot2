package vn.techmasterr.bookstore.repository;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import vn.techmasterr.bookstore.dto.NewBook;
import vn.techmasterr.bookstore.model.Book;
import vn.techmasterr.bookstore.utils.GenerateId;

@Repository
public class BookRepo {
  private ConcurrentHashMap<String, Book> books;
  
  public BookRepo() {
    books = new ConcurrentHashMap<>();
  }

  public Book createNewBook(NewBook newBook) {
    String bookId = GenerateId.generateUniqueId(5);
    Book book = new Book(bookId, newBook.title(), newBook.author());
    books.put(bookId, book);
    return book;
  }

  public List<Book> getAllSortByTitle() {
    return null;
  }

  public List<Book> searchByTitle(String keyword) {
    return null;
  }
}

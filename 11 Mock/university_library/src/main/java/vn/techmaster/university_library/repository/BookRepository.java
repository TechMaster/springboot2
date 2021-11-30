package vn.techmaster.university_library.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Repository;

import vn.techmaster.university_library.model.Book;

@Repository
public class BookRepository {
  private  HashMap<Book, Integer> bookInventory;
  public void addNewBook(Book book, int count) {

  }

  public Set<Book> getAll() {
    return bookInventory.keySet();   
  }

  public Optional<Book> findById(String id) {
    return null;
  }

  public Set<Book> getBooksByTag(String tag){
    return  null;
  }



}

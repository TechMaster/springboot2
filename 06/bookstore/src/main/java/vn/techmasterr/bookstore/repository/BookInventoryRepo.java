package vn.techmasterr.bookstore.repository;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import vn.techmasterr.bookstore.exception.BookException;
import vn.techmasterr.bookstore.model.BookInventory;

@Repository
public class BookInventoryRepo {
  private ConcurrentHashMap<String, List<BookInventory>> bookInventory;
  //Nếu book không tồn tại hãy throws BookException
  public void updateInventory(String bookId, int amount) throws BookException {

  }
}

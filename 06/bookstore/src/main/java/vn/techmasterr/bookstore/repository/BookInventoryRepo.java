package vn.techmasterr.bookstore.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import vn.techmasterr.bookstore.exception.BookException;
import vn.techmasterr.bookstore.model.BookInventory;

@Repository
public class BookInventoryRepo {
  private ConcurrentHashMap<String, List<BookInventory>> bookInventory;
  private int currentID;
  public BookInventoryRepo() {
    currentID = 0;
  }

  public int getNextID() {
    currentID += 1;
    return currentID;
  }

  //Nếu book không tồn tại hãy throws BookException
  public void updateInventory(String bookId, int amount) throws BookException {
    var listBookInventory = bookInventory.get(bookId);
    if (listBookInventory == null) {
      listBookInventory = new ArrayList<BookInventory>();
    }
    listBookInventory.add(new BookInventory(getNextID(), bookId, amount, LocalDateTime.now()));
  }
}

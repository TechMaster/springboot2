package vn.techmasterr.bookstore.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import vn.techmasterr.bookstore.exception.BookException;
import vn.techmasterr.bookstore.model.Book;
import vn.techmasterr.bookstore.model.BookInventory;

@Repository
public class BookInventoryRepo {
  private ConcurrentHashMap<String, List<BookInventory>> bookInventories;
  private int currentID;
  public BookInventoryRepo() {
    currentID = 0;
    bookInventories = new ConcurrentHashMap<>();
  }

  public int getNextID() {
    currentID += 1;
    return currentID;
  }

  //Nếu book không tồn tại hãy throws BookException
  public void updateInventory(String bookId, int amount) {
    BookInventory bookInventory = new BookInventory(getNextID(), bookId, amount, LocalDateTime.now());
    if (bookInventories.containsKey(bookId)){
      bookInventories.get(bookId).add(bookInventory);
    } else {
      bookInventories.put(bookId, List.of(bookInventory));
    }
  }

  public BookInventory getLastestBookInventory(String bookId) throws Exception {
      List<BookInventory> bookInventory = bookInventories.get(bookId);
      try {
        return bookInventory.get(bookInventory.size() - 1);
      }
      catch(Exception e){
        e.printStackTrace();
      }
      return null;
      
  }
  public BookInventory getLowStockBooks(String bookId) throws Exception {
    BookInventory bookInventoryLow = getLastestBookInventory(bookId);
         if (bookInventoryLow.getAmount() == 1){
        return bookInventoryLow;
  }
  return null;
}

public boolean checkExistedId(String id){
  return bookInventories.contains(id);
}

}
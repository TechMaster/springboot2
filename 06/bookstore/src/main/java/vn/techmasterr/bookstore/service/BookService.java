package vn.techmasterr.bookstore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.github.javafaker.Faker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import vn.techmasterr.bookstore.dto.BuyBook;
import vn.techmasterr.bookstore.dto.NewBook;
import vn.techmasterr.bookstore.event.LowStock;
import vn.techmasterr.bookstore.exception.BookException;
import vn.techmasterr.bookstore.model.Book;
import vn.techmasterr.bookstore.model.BookInventory;
import vn.techmasterr.bookstore.repository.BookInventoryRepo;
import vn.techmasterr.bookstore.repository.BookRepo;

@Service
public class BookService {
  @Autowired
  private BookRepo bookRepo;
  @Autowired
  private BookInventoryRepo bookInventoryRepo;

  @Autowired
    ApplicationEventPublisher applicationEventPublisher;


  /*
  
  */
  public Book CreateNewBook(NewBook newBook) {
    Book book = bookRepo.createNewBook(newBook);
    bookInventoryRepo.updateInventory(book.getId(), newBook.amount());
    return book;
  }

  public void Create1000Books() {
    Faker faker = new Faker();
    for (int i = 0; i < 10; i++) {
      CreateNewBook(new NewBook(faker.book().author(), faker.book().title(), faker.number().numberBetween(0, 100)));
    }
  }

  public Optional<Book> findById(String id) {
    return null;
  }


  /*
   * Lấy danh sách Book Inventory thấp số lượng amount bằng 0 hoặc 1
   */

  public BookInventory getLastestBookInventory(String bookId) throws Exception {
    return bookInventoryRepo.getLastestBookInventory(bookId);
  }

  public List<BookInventory> getLowStockBooks() throws Exception {
    List<BookInventory> bookInventories = new ArrayList<>();

    for (Book book : bookRepo.getAllBook()) {
      BookInventory lowStockBooks = bookInventoryRepo.getLowStockBooks(book.getId());

      if ( lowStockBooks!= null) {
        bookInventories.add(lowStockBooks);
      }
    }
    return bookInventories;
  }

  public void buyBook(BuyBook buyBook) throws Exception {
    if (bookInventoryRepo.checkExistedId(buyBook.bookId())) {
        int currAmount = bookInventoryRepo.getLastestBookInventory(buyBook.bookId()).getAmount();
        if (currAmount > buyBook.amount()) {
            bookInventoryRepo.updateInventory(buyBook.bookId(), currAmount - buyBook.amount());
        } else {
            applicationEventPublisher.publishEvent(new LowStock(this, "low stock", buyBook.bookId(), buyBook.amount()));
        }
    }

   
}

public List<Book> getAllBook(){
  return bookRepo.getAllBook();
}

@EventListener
public void importBook(LowStock lowStock) throws Exception{
    int currAmount = bookInventoryRepo.getLastestBookInventory(lowStock.getBookId()).getAmount();
    bookInventoryRepo.updateInventory(lowStock.getBookId(), currAmount + lowStock.getAmount());
}
}

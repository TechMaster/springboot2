package vn.techmasterr.bookstore.service;

import java.util.List;
import java.util.Optional;

import com.github.javafaker.Faker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.techmasterr.bookstore.dto.BuyBook;
import vn.techmasterr.bookstore.dto.NewBook;
import vn.techmasterr.bookstore.exception.BookException;
import vn.techmasterr.bookstore.model.Book;
import vn.techmasterr.bookstore.model.BookInventory;
import vn.techmasterr.bookstore.repository.BookInventoryRepo;
import vn.techmasterr.bookstore.repository.BookRepo;

@Service
public class BookService {
  @Autowired  private BookRepo bookRepo;
  @Autowired  private BookInventoryRepo bookInventoryRepo;

  /*

  */
  public Book CreateNewBook(NewBook newBook) {
    Book book = bookRepo.createNewBook(newBook);
    try {
      bookInventoryRepo.updateInventory(book.getId(), newBook.amount());      
    } catch (BookException e) {
      e.printStackTrace();      
    }
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
   * Kiểm tra nếu BookInventory có đủ sách, thì đơn hàng thành công, Nếu
   * BookInventory không đủ sách thì báo người dùng, sẽ đặt thêm sách, khi nào
   * sách về thì liên hệ lại
   */
  public void buyBook(BuyBook buyBook) {

  }


  /*
  Lấy danh sách Book Inventory thấp số lượng amount bằng 0 hoặc 1
  */
  public List<BookInventory> getLowStockBooks() {
    return null;
  }

}

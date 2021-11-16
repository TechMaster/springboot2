package vn.techmasterr.bookstore.service;

import java.util.Optional;

import com.github.javafaker.Faker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import vn.techmasterr.bookstore.dto.BuyBook;
import vn.techmasterr.bookstore.dto.NewBook;
import vn.techmasterr.bookstore.exception.BookException;
import vn.techmasterr.bookstore.model.Book;
import vn.techmasterr.bookstore.repository.BookInventoryRepo;
import vn.techmasterr.bookstore.repository.BookRepo;

@Service
@EnableScheduling
@ConditionalOnExpression("true")
public class BookService {
  @Autowired
  private BookRepo bookRepo;
  @Autowired
  private BookInventoryRepo bookInventoryRepo;

  public BookService() {
    Create1000Books();
  }

  public Book CreateNewBook(NewBook newBook) {
    Book book = bookRepo.createNewBook(newBook);
    try {
      bookInventoryRepo.updateInventory(book.getId(), newBook.amount());
      return book;
    } catch (BookException e) {
      e.printStackTrace();
    }
  }

  public void Create1000Books() {
    Faker faker = new Faker();
    for (int i = 0; i < 1000; i++) {
      CreateNewBook(new NewBook(faker.book().author(), faker.book().title(), faker.number().numberBetween(0, 100)));
      //Hãy viết hàm update inventory với BookId và newBook.getAmount
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
   * Quét tất cả các bản ghi BookInventory, nếu amount = 1 thì tạo Event tiến hành
   * Order thêm 5 quyển nữa
   */
  @Scheduled(fixedDelay = 1000)
  public void checkInventory() throws InterruptedException {

  }
}

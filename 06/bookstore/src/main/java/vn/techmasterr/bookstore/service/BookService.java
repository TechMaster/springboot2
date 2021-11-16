package vn.techmasterr.bookstore.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import vn.techmasterr.bookstore.dto.BuyBook;
import vn.techmasterr.bookstore.dto.NewBook;
import vn.techmasterr.bookstore.model.Book;
import vn.techmasterr.bookstore.repository.BookInventoryRepo;
import vn.techmasterr.bookstore.repository.BookRepo;

@Service
@EnableScheduling
@ConditionalOnExpression("true")
public class BookService {
  @Autowired private BookRepo bookRepo;
  @Autowired private BookInventoryRepo bookInventoryRepo;

  public void CreateNewBook(NewBook newBook) {
    
  }

  public void Generate1000Books() {

  }

  public Optional<Book> findById(String id) {
    return null;
  }

  /*
  Kiểm tra nếu BookInventory có đủ sách, thì đơn hàng thành công,
  Nếu BookInventory không đủ sách thì báo người dùng, sẽ đặt thêm sách, khi nào sách về thì liên hệ lại
  */
  public void buyBook(BuyBook buyBook) {

  }

  /*
  Quét tất cả các bản ghi BookInventory, nếu amount = 1 thì tạo Event tiến hành Order thêm 5 quyển nữa
  */
  @Scheduled(fixedDelay = 1000)
  public void checkInventory() throws InterruptedException {

  } 
}

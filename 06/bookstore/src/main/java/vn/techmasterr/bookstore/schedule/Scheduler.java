package vn.techmasterr.bookstore.schedule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;

import vn.techmasterr.bookstore.model.BookInventory;
import vn.techmasterr.bookstore.service.BookService;

@Component
@EnableScheduling
@ConditionalOnExpression("false")
public class Scheduler {
  @Autowired private BookService bookService; 
  
  /*
   * Quét tất cả các bản ghi BookInventory, nếu amount = 1 thì tạo Event LowStock
   */
  @Scheduled(fixedDelay = 1000)
  public void checkInventory() throws InterruptedException {
    List<BookInventory> lowInventoryBook = bookService.getLowStockBooks();
  }
}

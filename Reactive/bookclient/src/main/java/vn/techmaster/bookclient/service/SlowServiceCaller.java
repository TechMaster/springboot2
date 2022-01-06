package vn.techmaster.bookclient.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import vn.techmaster.bookclient.dto.Book;
/*
Component Service chứa các method nếu được đánh dấu @Async sẽ chạy trên các thread của Executor
*/
@Service
public class SlowServiceCaller {

  @Autowired
  private RestTemplate restTemplate;

  @Async
  /*
  Annotation @Async chỉ định hàm này sẽ chạy ở thread riêng chứ không chạy trong caller thread
  CompletableFuture hứa hẹn một kết quả trả về trong tương lai
  Nếu bỏ @Async tác vụ này luôn được gọi trong thread của caller dẫn đến thời gian  hoàn thành 10 tác vụ khoảng 20 giây.
  */
  public CompletableFuture<Book> getRandomBookFromBookStore() {
    String localSlowServiceEndpoint = "http://localhost:9000/api/randombook";
    ResponseEntity<Book> bookResponse = restTemplate.getForEntity(localSlowServiceEndpoint, Book.class);
    return CompletableFuture.completedFuture(bookResponse.getBody());
  }

  /*
  Hàm này không sử dụng Async
  */
  public Book getRandomBookFromBookStore2() {
    String localSlowServiceEndpoint = "http://localhost:9000/api/randombook";
    ResponseEntity<Book> bookResponse = restTemplate.getForEntity(localSlowServiceEndpoint, Book.class);
    return bookResponse.getBody();
  }
}
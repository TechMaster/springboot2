package vn.techmaster.bookclient.controller;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.techmaster.bookclient.dto.Book;
import vn.techmaster.bookclient.service.SlowServiceCaller;

@RestController
public class HomeController {

  @Autowired SlowServiceCaller slowServiceCaller;

  @GetMapping("/books")
  public String getSomeRandomBooks() throws InterruptedException, ExecutionException {
    Instant start = Instant.now();
    List<CompletableFuture<Book>> allFutures = new ArrayList<>();

    for (int i = 0; i < 10; i++) {
      //slowServiceCaller.getRandomBookFromBookStore() là một method @Async trả về CompletableFuture
      allFutures.add(slowServiceCaller.getRandomBookFromBookStore());
    }
    CompletableFuture.allOf(allFutures.toArray(new CompletableFuture[0]))
    .join();  //Chờ tất cả CompletableFuture xong thì nhập vào với thread hàm này để cùng chạy tiếp

    for (int i = 0; i < 10; i++) {
      System.out.println("response: " + allFutures.get(i).get().toString());
    }

    return "Total time: " + Duration.between(start, Instant.now()).toMillis() + " milli seconds";
  }

  /*
  Đây là một phương án khác. Chúng ta không đánh dấu @Asyn trong method slowServiceCaller.getRandomBookFromBookStore2
  Nhưng khi gọi thì dùng cú pháp CompletableFuture.supplyAsync
  */
  @GetMapping("/books2")
  public String getSomeRandomBooks2() throws InterruptedException, ExecutionException {
    Instant start = Instant.now();
    List<CompletableFuture<Book>> allFutures = new ArrayList<>();

    for (int i = 0; i < 10; i++) {
      allFutures.add(CompletableFuture.supplyAsync(() -> {
        //Hãy thử in tên thread ở đây      
        return slowServiceCaller.getRandomBookFromBookStore2();
      }));
    }
    CompletableFuture.allOf(allFutures.toArray(new CompletableFuture[0]))
    .join();  //Chờ tất cả CompletableFuture xong thì nhập vào với thread hàm này để cùng chạy tiếp

    for (int i = 0; i < 10; i++) {
      System.out.println("response: " + allFutures.get(i).get().toString());
    }

    return "Total time: " + Duration.between(start, Instant.now()).toMillis() + " milli seconds";
  }
}

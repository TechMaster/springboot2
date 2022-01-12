package vn.techmaster.bookclientnetty.controller;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;
import vn.techmaster.bookclientnetty.dto.Book;

@RestController
public class HomeController {

  @Autowired
  WebClient webClient;

  @GetMapping("/books")
  public String getSomeRandomBooks() throws InterruptedException, ExecutionException {
    Instant start = Instant.now();
    for (int i = 0; i < 100; i++) {
      webClient.get().uri("/api/randombook").accept(MediaType.APPLICATION_JSON)
          .retrieve() //exchange?
          .bodyToMono(Book.class)
          .doOnSuccess(book -> System.out.println(Thread.currentThread() + " gets book " + book.title()))
          .subscribe();
    }

    //Việc đo đếm thời gian ở đây không còn đúng nữa !
    return "Total time: " + Duration.between(start, Instant.now()).toMillis() + " milli seconds";
  }

  @GetMapping("/books2")
  /*
  Đây là cách viết thứ 2 kết quả tương tự cách số 1
  */
  public String getSomeRandomBooks2() throws InterruptedException, ExecutionException {
    Instant start = Instant.now();

    for (int i = 0; i < 100; i++) {
      Mono<Book> bookMono = webClient.get()
      .uri("/api/randombook")
      .retrieve()
      .bodyToMono(Book.class);

      bookMono.subscribe(book -> System.out.println(Thread.currentThread() + " gets book " + book.title()));
    }

    //Việc đo đếm thời gian ở đây không còn đúng nữa !
    return "Total time: " + Duration.between(start, Instant.now()).toMillis() + " milli seconds";
  }

  /*
  TODO: Hãy thử combine kết quả trả về từ các Mono Request sau đó in ra màn hình
  https://www.vinsguru.com/reactive-programming-reactor-combining-multiple-sources-of-flux-mono/

  Xây dựng một hệ thống Reactive
  https://www.baeldung.com/java-reactive-systems
  */
}

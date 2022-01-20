package vn.techmaster.demoflux;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import reactor.core.publisher.Flux;

public class FluxTest {
  @Test
  /*
  flux.map() sẽ tạo ra một đối tượng Flux mới. Do đó flux2 sẽ khác flux
  */
	void testMap1() {
		Flux<String> flux = Flux.just("A");
		Flux<String> flux2 = flux.map(s -> "foo" + s);
    assertThat(flux2).isNotEqualTo(flux);
		flux.subscribe(System.out::println);
    flux2.subscribe(System.out::println);
	}

  @Test
  /*

  */
	void testMap2() {
		Flux<String> flux = Flux.just("A");
		flux.map(s -> "foo" + s).subscribe(System.out::println);
	}

  @Test
  void testFluxMap() {
    Flux<String> cars = Flux.just("Fiat", "Audi", "BMW");
    cars.map(t -> t.toUpperCase())
        .subscribe(System.out::println);
  }

  @Test
  void testFluxfromIterable() {
    List<String> list = Arrays.asList("Fadil", "Lux", "VF5", "VF7");
    Flux.fromIterable(list)
        .map(item -> item.toLowerCase())
        .subscribe(System.out::println);
  }

  @Test
  /*
   * Ghép hai luồng dữ liệu lại, giống hình ảnh hai vạt của khoá kéo
   */
  void testFluxZip() {
    Flux<String> cars = Flux.just("Fiat", "Audi", "BMW");
    Flux<String> years = Flux.just("2009", "2018", "2015");

    Flux.zip(cars, years)
        .map(t -> t.getT1() + " " + t.getT2())
        .subscribe(System.out::println);
  }

  @Test
  /*
  .log() dùng để log dữ liệu khi subscribe từng phần tử xuất hiện từ Flux. Kết quả là
  21:57:46.233 [main] INFO reactor.Flux.Array.1 - | request(unbounded)
  21:57:46.233 [main] INFO reactor.Flux.Array.1 - | onNext(1)
  21:57:46.233 [main] INFO reactor.Flux.Array.1 - | onNext(2)
  21:57:46.233 [main] INFO reactor.Flux.Array.1 - | onNext(3)
  21:57:46.234 [main] INFO reactor.Flux.Array.1 - | onNext(4)
  21:57:46.234 [main] INFO reactor.Flux.Array.1 - | onComplete()
  */
  void testFluxLog() {
    List<Integer> elements = new ArrayList<>();
    Flux.just(1, 2, 3, 4)
        .log()
        .subscribe(elements::add);
    assertThat(elements).containsExactly(1, 2, 3, 4);
  }

  @Test
  /*
   * hàm Flux.interval định thời tạo ra một phần tử dữ liệu
   */
  void testFluxInterval() throws InterruptedException {
    Flux.interval(Duration.ofMillis(100))
        .map(item -> "tick: " + item)
        .map(item -> item.toUpperCase()) // Bổ xung bao nhiêu hàm map nối tiếp nhau cũng được
        .take(10) // chỉ lấy ra 10 phần tử rồi dừng bộ đếm
        .subscribe(System.out::println);

    // Phải có dòng lệnh này thì hàm mới chờ để in đủ 10 dòng
    Thread.sleep(1200);
  }


}

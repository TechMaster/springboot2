package vn.techmaster.demoflux;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import com.github.javafaker.Beer;
import com.github.javafaker.Book;
import com.github.javafaker.Faker;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.util.function.Tuple2;

@Slf4j
class MonoTest {

	@Test
	void testEmptyMono() {
		Mono<String> noData = Mono.empty();
		noData.subscribe(data -> {
			assertThat(data).isNull();
		});
	}

	@Test
	void testStringMono() {
		Mono<String> monoString = Mono.just("Hello World");
		monoString.subscribe(data -> {
			assertThat(data).isEqualTo("Hello World");
		});
	}

	@Test
	/*
	 * Giả lập một tác vụ cần 1000 ms mới trả về dữ liệu kiểu String
	 */

	void testLongTaskReturnMono() {

		Instant start = Instant.now(); // Tính mốc thời gian
		Mono<String> monoString = Mono.fromCallable(() -> {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
			return "1 second delay string";
		});

		monoString.subscribe(data -> {
			assertThat(data).isEqualTo("1 second delay string");
			log.info("Total time: " + Duration.between(start, Instant.now()).toMillis() + " milli seconds");
		});
	}

	@Test
	/*
	 * Tạo Mono từ một CompletableFuture
	 * Trường hợp này phải dùng Thread.sleep rất dở
	 */
	void testMonoFromFuture() throws InterruptedException {
		Supplier<String> guessAWord = () -> {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName());
			return "This is magic";
		};

		CompletableFuture<String> future = CompletableFuture.supplyAsync(guessAWord);
		var aMono = Mono.fromFuture(future);
		aMono.subscribe(System.out::println);
		Thread.sleep(2100);
	}

	@Test
	/*
	 * Tạo Mono từ một CompletableFuture. Cách
	 */
	void testMonoFromFutureBlock() throws InterruptedException {
		Supplier<String> guessAWord = () -> {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName());
			return "This is magic";
		};

		CompletableFuture<String> future = CompletableFuture.supplyAsync(guessAWord);
		var aMono = Mono.fromFuture(future);
		aMono.subscribe(System.out::println);
		System.out.println("Đoạn lệnh này sẽ in ra trước");
		// Dùng block sẽ chờ khi nào aMono có kết quả trả về
		aMono.block();
	}

	@Test
	/*
	 * Mono<Book> one = this.webClient...
	 * Mono<Bear> two = this.webClient...
	 * 
	 * // we want all requests to happen concurrently
	 * Mono<Void> all = Mono.when(one, two);
	 * // we subscribe and then wait for all to be done
	 * all.block();
	 */
	void testMonoWhenBlock() {
		Faker faker = new Faker();
		Supplier<Book> getBook = () -> {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName());
			return faker.book();
		};

		var one = Mono.fromFuture(CompletableFuture.supplyAsync(getBook));
		one.subscribe(System.out::println);

		Supplier<Beer> getBeer = () -> {
			try {
				Thread.sleep(1200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName());
			return faker.beer();
		};

		var two = Mono.fromFuture(CompletableFuture.supplyAsync(getBeer));
		two.subscribe(System.out::println);

		Mono<Void> all = Mono.when(one, two); // Tạo một Mono chờ khi cả 2 Mono hoàn thành
		all.block(); // thực sự block thread này để đợi kết quả
	}

	@Test
	void testMonoZip() {
		Faker faker = new Faker();
		Supplier<Book> getBook = () -> {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName());
			return faker.book();
		};

		var one = Mono.fromFuture(CompletableFuture.supplyAsync(getBook));
		Supplier<Beer> getBeer = () -> {
			try {
				Thread.sleep(1200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName());
			return faker.beer();
		};

		var two = Mono.fromFuture(CompletableFuture.supplyAsync(getBeer));

		Mono<Tuple2<Book, Beer>> all = Mono.zip(one, two); // Ghép hai dữ liệu trả về vào kiểu Tuple2

		// Viết hàm hứng dữ liệu trả về
		all.subscribe((data) -> {
			System.out.println("book " + data.getT1() + ", beer " + data.getT2());
		});
		all.block(); // Cần phải có lệnh này thì
	}

	@Test
	/*
	 * Nếu lỗi xuất hiện trong Mono
	 */
	void testMonoerror() {
		Mono<?> mono = Mono.just("Hello World")
				.then(Mono.error(new RuntimeException("Cannot connect to REST")));


		StepVerifier
				.create(mono)
				.expectErrorMatches(throwable -> throwable instanceof RuntimeException &&
						throwable.getMessage().equals("Cannot connect to REST"))
				.verify();
	}

}
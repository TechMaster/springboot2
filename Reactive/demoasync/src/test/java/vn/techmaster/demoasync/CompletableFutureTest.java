package vn.techmaster.demoasync;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Supplier;
import java.time.Instant;
import java.time.Duration;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class CompletableFutureTest {
	long factorial(long n) {
		if (n == 0) {
			return 1;
		} else
			return (n * factorial(n - 1));
	}

	@Test
	/*
	 * Ví dụ tham khảo từ bài viết này
	 */
	void launchThread() {
		System.out.println(Thread.currentThread().getName()); // main
		long number = 20;
		Thread newThread = new Thread(() -> {
			System.out.println(Thread.currentThread().getName()); // Thread-0
			System.out.println("Factorial of " + number + " is: " + factorial(number));
		});
		newThread.start();
	}

	@Test
	/*
	 * Trước Java 8, chúng ta sử dụng Future trong thư viện java.util.concurrent
	 * rồi kiểm tra xem Future hoàn thành chưa bằng hàm isDone()
	 */
	void test_Future() throws InterruptedException, ExecutionException {
		ExecutorService threadpool = Executors.newCachedThreadPool();
		long number = 20L;
		Future<Long> futureTask = threadpool.submit(() -> factorial(number));

		while (!futureTask.isDone()) {
			System.out.println("FutureTask is not finished yet...");
		}
		long result = futureTask.get();
		System.out.println("Factorial of " + number + " is: " + result);
		threadpool.shutdown();
	}

	@Test
	/*
	 * Sử dụng CompletableFuture một tính năng trong Java 8 không cần đến
	 * ExecutorService
	 * Chạy một tác vụ async không trả về kết quả
	 */
	void runVoidAsyncTask() throws InterruptedException, ExecutionException {
		Runnable myTask = () -> {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName());
		};

		CompletableFuture<Void> future = CompletableFuture.runAsync(myTask);
		future.get();
	}

	@Test
	/*
	Bài test này demo hàm supplyAsync dùng để chạy một hàm trong một thread khác, trả về kết quả trong tương lai.
  Sau đó dùng future.get() để lấy dữ liệu trả về
	*/
	void runAsyncTaskReturnString() throws InterruptedException, ExecutionException {
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
		System.out.println(future.get()); // Đoạn này sẽ block cho đến khi kết quả trả về
	}

	@Test
	/*
	 * Sử dụng whenComplete để hứng thời điểm CompletableFuture hoàn thành
	 */
	void whenCompleteAsyncTaskReturnString() throws InterruptedException, ExecutionException {
		Supplier<String> guessAWord = () -> {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName());
			return "This is magic";
		};

		CompletableFuture<String> future = CompletableFuture.supplyAsync(guessAWord);
		// Tạo sự kiện khi future complete
		future.whenComplete((result, exception) -> {
			if (exception != null) {
				System.out.println("exception occurs");
				System.err.println(exception);
			} else {
				System.out.println("no exception, got result: " + result);
			}
		});
		Thread.sleep(1500);// Nếu không có lệnh chờ này, hàm chính sẽ thoát trước khi future complete
	}

	@Test
	/*
	 * Sử dụng CompletableFuture.join() để nhập một tác vụ vào thread hiện tại
	 * The CompletableFuture. join() method is similar to the get method,
	 * but it throws an unchecked exception in case the Future does not complete
	 * normally.
	 * This makes it possible to use it as a method reference in the Stream. map()
	 * method.
	 */
	void join() throws InterruptedException {
		Supplier<String> guessAWord = () -> {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName());
			return "This is magic";
		};

		CompletableFuture<String> future = CompletableFuture.supplyAsync(guessAWord);
		future.whenComplete((result, exception) -> {
			if (exception != null) {
				System.out.println("exception occurs");
				System.err.println(exception);
			} else {
				System.out.println("no exception, got result: " + result);
			}
		});

		future.join();

	}

	@Test
	/*
	 * Tham khảo code
	 * https://www.logicbig.com/tutorials/core-java-tutorial/java-multi-threading/
	 * completion-stage-when-complete.html
	 * chú ý hàm supplyAsync, whenComplete, thenApply, thenAccept
	 * khác biệt thenApply và thenAccept là gì?
	 */
	void apply_accept_run() {
		int i = 10;
		System.out.printf("-- input: %s --%n", i);
		CompletableFuture
				.supplyAsync(() -> { // Truyền vào biểu thức Lambda
					return 16 / i;
				})
				.whenComplete((input, exception) -> { // Khi biểu thức hoàn thành
					if (exception != null) {
						System.out.println("exception occurs");
						System.err.println(exception);
					} else {
						System.out.println("no exception, got result: " + input);
					}
				})
				.thenApply(input -> input * 3) // Thực hiện tiếp
				.thenApply(input -> input * 2) // Thực hiện tiếp bước nữa
				.thenAccept(System.out::println) // Nhận kết quả
				.thenRun(() -> {
					System.out.println("Thực sự kết thúc");
				});
	}

	@Test
	/*
	 * Chạy đồng thời nhiều task sử dụng hàm allOf
	 * Vì mỗi tác vụ đều chạy ở thread riêng, do đó thời gian hoàn thành chỉ nhỉnh hơn tác vụ muộn nhất chút xíu
	 */
	void run_multiple_future_paralell() throws InterruptedException, ExecutionException {
		CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "Hello";
		});
		CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "Beautiful";
		});
		CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> "World");
		Instant start = Instant.now();
		CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(future1, future2, future3);
		combinedFuture.get();

		System.out.println(Duration.between(start, Instant.now()).toMillis() + " milliseconds");

		future1.thenAccept(System.out::println);
		future2.thenAccept(System.out::println);
		future3.thenAccept(System.out::println);

		assertThat(future1.isDone());
		assertThat(future2.isDone());
		assertThat(future3.isDone());
	}

	@Test
	/*
	 * Hàm anyOf
	 * Khi bất kỳ một trong các task thành công thì trả về.
	 */
	void any_Of() throws InterruptedException, ExecutionException {
		CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "Hello";
		});
		CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "Beautiful";
		});
		CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> "World");

		CompletableFuture<Object> combinedFuture = CompletableFuture.anyOf(future1, future2, future3);

		System.out.println(combinedFuture.get());
	}

	@Test
	/*
	 * thenCompose: tổng hợp kết quả tác vụ trước với tác vụ tiếp theo.
	 * Cả 2 cùng chạy trên 1 thread. Thời gian hoàn thành bằng tổng thời gian chạy 2 tác vụ
	 * System.out.println(Thread.currentThread().getName()) in ra
	 * "ForkJoinPool.commonPool-worker-1" 2 lần
	 */
	void thenCompose() throws InterruptedException, ExecutionException {
		CompletableFuture<String> completableFuture = CompletableFuture
				.supplyAsync(() -> {
					System.out.println(Thread.currentThread().getName());
					return "Hello";
				})
				.thenCompose(s -> CompletableFuture.supplyAsync(() -> {
					System.out.println(Thread.currentThread().getName());
					return s + " World";
				}));

		assertThat(completableFuture.get()).isEqualTo("Hello World");
	}
	@Test
	/*
	thenComposeAsync sẽ chạy ở 2 thread khác nhau, thời gian hoàn thành ngay sau tác vụ cuối cùng
	*/
	void thenComposeAsync() throws InterruptedException, ExecutionException {
		CompletableFuture<String> completableFuture = CompletableFuture
				.supplyAsync(() -> {
					System.out.println(Thread.currentThread().getName());
					return "Hello";
				})
				.thenComposeAsync(s -> CompletableFuture.supplyAsync(() -> {
					System.out.println(Thread.currentThread().getName());
					return s + " World";
				}));

		assertThat(completableFuture.get()).isEqualTo("Hello World");
	}

	@Test
	/*
	thenAcceptBoth và thenAcceptBothAsync đều xử lý kết quả trả về từ 2 tác vụ
	thenAcceptBothAsync chạy 2 tác vụ ở 2 thread khác nhau
	*/
	void thenAcceptBoth() {
		CompletableFuture future = CompletableFuture.supplyAsync(() -> "Hello")
				.thenAcceptBoth(CompletableFuture.supplyAsync(() -> " World"),
						(s1, s2) -> System.out.println(s1 + s2));
	}

	@Test
	/*
	Tính năng trong JDK 9, bổ xung thêm whenCompleteAsync. Nó khác với whenComplete ở chỗ sẽ chạy ở
	thread riêng khi sử dụng ExecutorService
	*/
	void whenCompleteAsync() {
		ExecutorService service = Executors.newFixedThreadPool(10);
		CompletableFuture<String> completableFuture = CompletableFuture
				.supplyAsync(() -> {
					System.out.println("suppyAsync " + Thread.currentThread().getName());
					return "Hello";
				}, service)
				.whenCompleteAsync((result, exception) -> {
					System.out.println("whenCompleteAsync " + Thread.currentThread().getName());
					System.out.println(result);
				});
		//completableFuture.join();
	}

	@Test
	/*
	exceptionally dùng để bắt Exception ném ra từ đoạn lệnh trước đó.
	*/
	void exceptionally(){
		CompletableFuture
				.supplyAsync(() -> {
					throw new RuntimeException("Failed to connect DB");
				})
				.exceptionally(e -> e.getMessage())
				.thenAccept(System.out::println);
	}
}

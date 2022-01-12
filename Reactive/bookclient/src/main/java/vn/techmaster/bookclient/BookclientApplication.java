package vn.techmaster.bookclient;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableAsync
public class BookclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookclientApplication.class, args);
	}

	@Bean
	//RestTemplate là một blocking web client
	public RestTemplate restTemplate() {
			return new RestTemplate();
	}

	@Bean
	/*
	Cấu hình bean Executor sử dụng ThreadPool.
	Trong ví dụ này chúng ta tạo ra 10 request sang BookStore. Mỗi request cần 2000 milliseconds để hoàn thành
	Nếu xử lý tuần tự sẽ cần 20000 msec (20 sec) để hoàn thành.
	Nếu dùng 2 thread đồng thời, số thời gian sẽ giảm xuống khoảng 10 sec
	Nếu dùng 4 thread đồng thời, số thời gian sẽ giảm xuống khoảng 6 sec	
	Nhưng nếu dùng 5 thread đồng thời, thời gian chạy giảm xuống 4 sec
	Nếu dùng 8 thread đồng thời,  thời gian chạy vẫn loanh quanh 4 sec
	Nếu dùng 10 thread đồng thời, thời gian chạy giảm xuống 2 sec
	*/
	public Executor executor() {
			ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
			executor.setCorePoolSize(30);
			executor.setMaxPoolSize(30);
			executor.setQueueCapacity(500);
			executor.initialize();
			return executor;
	}

}

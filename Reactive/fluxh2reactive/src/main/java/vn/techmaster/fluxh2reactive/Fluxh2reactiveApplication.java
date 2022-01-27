package vn.techmaster.fluxh2reactive;

import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vn.techmaster.fluxh2reactive.model.Post;
import vn.techmaster.fluxh2reactive.repository.PostRepo;

@SpringBootApplication
public class Fluxh2reactiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(Fluxh2reactiveApplication.class, args);
	}

	/*
	Bình thường trong dự án Spring Boot WebFlux, thì không thể truy cập h2 console
	Cấu hình để H2-Console có thể xem được phải dùng cổng khác với cổng Netty đang phục vụ
	*/
	@Component
	public class H2 {

		private org.h2.tools.Server webServer;
		private org.h2.tools.Server tcpServer;

		@EventListener(org.springframework.context.event.ContextRefreshedEvent.class)
		public void start() throws java.sql.SQLException {
			this.webServer = org.h2.tools.Server.createWebServer("-webPort", "8082", "-tcpAllowOthers").start();
			this.tcpServer = org.h2.tools.Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers").start();
		}

		@EventListener(org.springframework.context.event.ContextClosedEvent.class)
		public void stop() {
			this.tcpServer.stop();
			this.webServer.stop();
		}
	}

	/*
	*/
	@Component
	@Slf4j
	@RequiredArgsConstructor
	class DataInitializer implements ApplicationRunner {

		private final PostRepo posts;

		@Override
		public void run(ApplicationArguments args) throws Exception {
			log.info("start data initialization...");
			this.posts
					.saveAll(
							List.of(
									Post.builder().title("Ronaldo comes back MU").content("He is good striker").build(),
									Post.builder().title("Trịnh Văn Quyết bán khống cổ phiếu").content("Phạt ngay 1.5 tỷ").build()))
					.thenMany(
							this.posts.findAll())
					.subscribe((data) -> log.info("post:" + data),
							(err) -> log.error("error" + err),
							() -> log.info("initialization is done..."));
		}
	}

}

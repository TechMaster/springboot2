package vn.techmaster.bookclientnetty.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.channel.nio.NioEventLoopGroup;

@Configuration
public class AppConfig {
  @Bean
  /*
  Tạo nhóm 5 thread để phục vụ Non Blocking IO
  Cũng giống như cửa hàng có 5 nhân viên bán hàng cùng phục vụ nhiều
  khách một lúc thay vì mỗi nhân viên chỉ phục vụ cho 1 khách hàng
  cho đến khi khách hàng mua xong
  */
  public NioEventLoopGroup nioEventLoopGroup() {
    return new NioEventLoopGroup(5);
  }

  @Bean
  /*
  Ở phiên bản BookClient Blocking chúng ta dùng RestTemplate
  Còn ở BookClientNetty chúng ta dùng WebClient hỗ trợ non-blocking 
  */
  public WebClient webClient(ReactorClientHttpConnector r) {
    // root url to localhost:9000, where our slow service should be running
    return WebClient.builder().baseUrl("http://localhost:9000").clientConnector(r).build();
  }
}

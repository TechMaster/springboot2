package vn.techmaster.bookstorenetty.config;

import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.boot.web.embedded.netty.NettyServerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import reactor.netty.http.server.HttpServer;

@Configuration
public class WebServerConfig {
  @Bean
  public NettyReactiveWebServerFactory nettyReactiveWebServerFactory() {
    NettyReactiveWebServerFactory webServerFactory = new NettyReactiveWebServerFactory();
    webServerFactory.addServerCustomizers(new EventLoopNettyCustomizer());
    return webServerFactory;
  }
}

class EventLoopNettyCustomizer implements NettyServerCustomizer {

  @Override
  public HttpServer apply(HttpServer httpServer) {
      EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
      eventLoopGroup.register(new NioServerSocketChannel());
      return httpServer.runOn(eventLoopGroup);
  }
}
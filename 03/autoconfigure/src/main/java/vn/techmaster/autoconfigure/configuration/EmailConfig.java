package vn.techmaster.autoconfigure.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:email.properties")
@ConfigurationProperties(prefix = "mail")
public class EmailConfig {
  private String hostName;
  private int port;
  private String from;
  @Override
  public String toString() {
    return "EmailConfig [from=" + from + ", hostName=" + hostName + ", port=" + port + "]";
  }
  public String getHostName() {
    return hostName;
  }
  public void setHostName(String hostName) {
    this.hostName = hostName;
  }
  public int getPort() {
    return port;
  }
  public void setPort(int port) {
    this.port = port;
  }
  public String getFrom() {
    return from;
  }
  public void setFrom(String from) {
    this.from = from;
  }
}

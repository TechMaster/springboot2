package vn.techmasterr.bookstore.event;

import org.springframework.context.ApplicationEvent;

public class LowStock extends ApplicationEvent {

  @Override
  public String toString() {
    return "CustomEvent [message=" + message + ", source = " + this.source + "]";
  }

  private String message;

  public LowStock(Object source, String message) {
    super(source);
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

}

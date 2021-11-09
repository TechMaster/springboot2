package vn.techmaster.autoconfigure.event;

import org.springframework.context.ApplicationEvent;

public class CustomEvent extends ApplicationEvent {
  @Override
  public String toString() {
    return "CustomEvent [message=" + message + ", source = " + this.source + "]";
  }
  private String message;

  public CustomEvent(Object source, String message) {
      super(source);
      this.message = message;
  }
  public String getMessage() {
      return message;
  }
}

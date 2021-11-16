package vn.techmasterr.bookstore.event;

import org.springframework.context.ApplicationEvent;

public class LowStock extends ApplicationEvent {
  @Override
  public String toString() {
    return "CustomEvent [message=" + message + ", source = " + this.source + "]";
  }


  private String message;
  private String bookId;
  private int amount;

  public LowStock(Object source, String message, String bookId, int amount) {
    super(source);
    this.message = message;
    this.bookId = bookId;
    this.amount = amount;
  }

  public String getBookId() {
    return bookId;
  }

  public int getAmount() {
    return amount;
  }
}


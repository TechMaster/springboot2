package vn.techmasterr.bookstore.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookInventory {
  private int id;
  private String bookId;
  private int amount;
  private LocalDateTime updateDate;
}

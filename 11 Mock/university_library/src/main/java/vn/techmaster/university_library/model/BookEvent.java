package vn.techmaster.university_library.model;

import java.time.LocalDateTime;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookEvent {
  private int id;
  private User user;
  private Set<Book> books;
  private Action action; //RENT: mượn, RETURNN: trả, EXTEND: gia hạn
  private LocalDateTime date;
}

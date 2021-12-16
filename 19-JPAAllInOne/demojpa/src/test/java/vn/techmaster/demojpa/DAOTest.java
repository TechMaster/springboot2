package vn.techmaster.demojpa;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.techmaster.demojpa.dao.Book;
import vn.techmaster.demojpa.dao.BookDao;

public class DAOTest {
  private BookDao bookDao = new BookDao("book.csv");
  @Test
  void getAll() {
    List<Book> books = bookDao.getAll();
    assertThat(books.size()).isGreaterThan(0);
  }

  @Test
  void getById() {
    Book book = bookDao.get(1).orElseThrow(() -> {
      return new RuntimeException("Book not found");
    });
    assertThat(book).isNotNull();
  }

}

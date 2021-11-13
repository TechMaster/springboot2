package vn.techmaster.bookinventory.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepo {
  @Autowired private BookInventoryRepo bookInventoryRepo;
  public void demo() {
    bookInventoryRepo.add();
  }
}

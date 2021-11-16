package vn.techmaster.bookinventory.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepo {
  @Autowired private BookInventoryRepo bookInventoryRepo;
  public BookRepo() {
    System.out.println("Default constructor");
  }
  
  public void demo() {
    bookInventoryRepo.add();
  }


  static {
    System.out.println("Run First");
  }
}

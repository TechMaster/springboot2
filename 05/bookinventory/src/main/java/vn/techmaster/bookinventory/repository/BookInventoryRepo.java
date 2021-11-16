package vn.techmaster.bookinventory.repository;

import org.springframework.stereotype.Repository;

@Repository
public class BookInventoryRepo {
  public BookInventoryRepo() {
    System.out.println("BookInventoryRepo - default constructor");
  }
  public void add() {
    System.out.println("Add");
  }
  static {
    System.out.println("BookInventoryRepo - static");
  }
}

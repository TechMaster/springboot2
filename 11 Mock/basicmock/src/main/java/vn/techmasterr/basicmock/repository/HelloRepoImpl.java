package vn.techmasterr.basicmock.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class HelloRepoImpl implements HelloRepo {

  @Override
  public String hi() {
    return "Hello World";
  }

  @Override
  public void foo() {
    System.out.println("Foo is called");  
  }

  @Override
  public List<String> bar(int number) {
    ArrayList<String> list = new ArrayList<String>();
    for (int i = 0; i < number; i++) {
      list.add(String.valueOf(i));
    }
    return list;
  }
  
}

package vn.techmaster.demojpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import vn.techmaster.demojpa.customrepo.PersonRepo;
import vn.techmaster.demojpa.model.Person;

@DataJpaTest
public class PersonRepoTest {
  @Autowired private PersonRepo personRepo;

  @Test
  void findPersonById() {
    Person person = personRepo.findById(1L).orElseThrow();
    System.out.println(person.getSex());
  }
}

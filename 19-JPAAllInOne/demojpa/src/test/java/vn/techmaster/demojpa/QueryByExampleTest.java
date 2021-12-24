package vn.techmaster.demojpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import vn.techmaster.demojpa.model.Person;
import vn.techmaster.demojpa.repository.PersonRepository;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class QueryByExampleTest {
  @Autowired
  private PersonRepository personRepo;

  @Test
  void queryByExample() {
    var soldierInHelsinki = Person.builder().city("Helsinki").job("Soldier").build();

    var people = personRepo.findAll(Example.of(soldierInHelsinki,
        ExampleMatcher.matching().withIgnorePaths("salary")
        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING))); // Loaị bỏ trường primitive

    people.forEach(person -> {
      assertThat(person)
          .hasFieldOrPropertyWithValue("city", "Helsinki")
          .hasFieldOrPropertyWithValue("job", "Soldier");
    });
  }
}

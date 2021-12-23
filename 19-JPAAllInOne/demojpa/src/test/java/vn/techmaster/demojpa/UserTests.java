package vn.techmaster.demojpa;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.github.javafaker.Faker;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import vn.techmaster.demojpa.model.User;
import vn.techmaster.demojpa.repository.UserRepository;
import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
public class UserTests {
  @Autowired private UserRepository userRepository;
  @Autowired private EntityManager em;

  @Test
  @Transactional
  void adduser() {
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    Faker faker = new Faker();
    User user = new User(
      faker.name().fullName(),
      df.format(faker.date().between(Date.valueOf("1930-01-01"), Date.valueOf("2021-12-01"))),
      faker.numerify("##########"),
      faker.internet().emailAddress());
    em.persist(user);
    em.flush();
    assertThat(user.getId()).isGreaterThan(0L);
  }
  @Test
  @Transactional
  void addUsers() {
    long numberUserBeforeAdd = userRepository.count();
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    for (int i = 0; i< 10; i++) {
      Faker faker = new Faker();
      User user = new User(
        faker.name().fullName(),
        df.format(faker.date().between(Date.valueOf("1930-01-01"), Date.valueOf("2021-12-01"))),
        faker.numerify("##########"),
        faker.internet().emailAddress());
      em.persist(user);
      System.out.println(user.getId());
    }

    em.flush();
    long numberUserAferAdd = userRepository.count();
  }
}

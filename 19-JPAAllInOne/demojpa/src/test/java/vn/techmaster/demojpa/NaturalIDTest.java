package vn.techmaster.demojpa;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

import vn.techmaster.demojpa.model.naturalid.Person;

@DataJpaTest
@Sql({"/naturalperson.sql"}) //Load dữ liệu trong lúc test
public class NaturalIDTest {
  @Autowired  private EntityManager em;

  @Test
  @Transactional
  void testNaturalID() {
    Person p1 = new Person();
    p1.setEmail("cuong@techmaster.vn");
    em.persist(p1);

    Session session = em.unwrap(Session.class);
    Person p2 = session.byNaturalId(Person.class).using("email", "cuong@techmaster.vn").load();

    assertThat(p1).isEqualTo(p2);

    Person p3 = session.byNaturalId(Person.class).using("email", "bill@microsoft.com").load();
    assertThat(p3.getEmail()).isEqualTo("bill@microsoft.com");
  }
  
}

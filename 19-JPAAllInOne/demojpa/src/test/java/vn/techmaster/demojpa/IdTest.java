package vn.techmaster.demojpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.EntityManager;

import vn.techmaster.demojpa.model.id.Bar;
import vn.techmaster.demojpa.model.id.TableID;

@DataJpaTest
public class IdTest {
  @Autowired  private TestEntityManager testEntityManager;
  @Autowired  private EntityManager em;

  @Test
  void randomIDGenerator() {
    Bar bar = new Bar();
    bar.setName("Elephant");
    String id = (String) testEntityManager.persistAndGetId(bar);
    assertThat(id).hasSizeGreaterThanOrEqualTo(10);
  }

  @Test
  void tableIdGenerator() {
    TableID r1 = new TableID();
    r1.setName("Buratino");
    em.persist(r1);
    //Long id1 = (Long) em.persistAndGetId(r1);
    System.out.println(r1.getId());

    TableID r2 = new TableID();
    r2.setName("Buratino2");
    //Long id2 = (Long) testEntityManager.persistAndGetId(r2);
    em.persist(r2);
    System.out.println(r2.getId());
    em.flush();
  }
}

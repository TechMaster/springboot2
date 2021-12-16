package vn.techmaster.demojpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.assertj.core.api.Assertions.assertThat;
import vn.techmaster.demojpa.model.id.Bar;
import vn.techmaster.demojpa.model.id.TableID;

@DataJpaTest
public class IdTest {
  @Autowired  private TestEntityManager testEntityManager;

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
    Long id1 = (Long) testEntityManager.persistAndGetId(r1);
    System.out.println(id1);

    TableID r2 = new TableID();
    r2.setName("Buratino");
    Long id2 = (Long) testEntityManager.persistAndGetId(r2);
    System.out.println(id2);
  }
}

package vn.techmaster.demojpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import vn.techmaster.demojpa.model.id.Bar;
import vn.techmaster.demojpa.model.id.StudentSubject;
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
  @Transactional
  void tableIdGenerator() {
    TableID r1 = new TableID();
    r1.setName("Titok");
    em.persist(r1);
    var id1 = r1.getId();


    TableID r2 = new TableID();
    r2.setName("Bilibli");
    //Long id2 = (Long) testEntityManager.persistAndGetId(r2);
    em.persist(r2);
    var id2 = r2.getId();

    em.flush();

    assertThat(em.find(TableID.class, id1)).isEqualTo(r1);
    assertThat(em.find(TableID.class, id2)).isEqualTo(r2);
  }

  @Test
  @Transactional
  void testCompositeKey(){
    StudentSubject ss1 = new StudentSubject("OX-11", "Math", 5);
    StudentSubject ss2 = new StudentSubject("OX-11", "English", 10);
    StudentSubject ss3 = new StudentSubject("OX-13", "Physics", 8);
    em.persist(ss1);
    em.persist(ss2);
    em.persist(ss3);
    em.flush();
    var query = em.createQuery("SELECT ss FROM StudentSubject ss", StudentSubject.class);
    List<StudentSubject> result = query.getResultList();
    assertThat(result).hasSize(3);
  }
}

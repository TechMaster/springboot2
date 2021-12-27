package vn.techmaster.demojpa;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import vn.techmaster.demojpa.model.AuditLog;

@DataJpaTest
public class EntityEventTest {
  @Autowired private EntityManager em;
  @Test
  @Transactional
  void testEventLifeCycleOfEntity() {
    AuditLog al = new AuditLog();
    al.setMessage("Version 1.0");
    em.persist(al);
    em.flush();

    al.setMessage("Version 1.1");
    em.persist(al);
    em.flush();

    em.remove(al);
    em.flush();
  }
}

package vn.techmaster.demojpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;

public class EMTest {
  @Autowired  private EntityManager em;
  @PersistenceContext private EntityManager em2;

  @Test
  void testEntityManagerSingleton() {
    assertThat(em).isEqualTo(em2);
  }
}

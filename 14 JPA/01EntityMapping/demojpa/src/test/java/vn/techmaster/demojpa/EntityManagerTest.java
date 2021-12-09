package vn.techmaster.demojpa;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import vn.techmaster.demojpa.model.mapping.Animal;

@DataJpaTest
public class EntityManagerTest {
  @Test
  @DisplayName("01. Entity Manager")
  public void entityManager_createAnimal() {
    //https://www.baeldung.com/hibernate-no-persistence-provider
    var sessionFactory = Persistence.createEntityManagerFactory("vn.techmaster.demojpa");
    EntityManager entityManager = sessionFactory.createEntityManager();
    entityManager.getTransaction().begin();
    entityManager.persist(new Animal("Trâu"));
    entityManager.persist(new Animal("Bò"));
    entityManager.persist(new Animal("Lợn"));
    entityManager.persist(new Animal("Gà"));
    entityManager.getTransaction().commit();
    entityManager.close();
  }
}

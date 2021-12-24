package vn.techmaster.demojpa;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import net.bytebuddy.utility.RandomString;
import vn.techmaster.demojpa.model.id.Bar;
import vn.techmaster.demojpa.repository.BarRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CRUDTest {
  @Autowired private EntityManager em;
  @Autowired private BarRepository barRepository;
  @Test
  void testCRUD() {
    Bar bar  = new Bar();
    bar.setName("Foo");
    em.persist(bar); //Create
    String id = bar.getId();
    Bar barInDB = em.find(Bar.class, id); //Query
    assertThat(barInDB.getName()).isEqualTo("Foo");
   
    barInDB.setName("New Foo");
    em.flush();
    em.merge(bar);  
    /* cập nhật thay đổi từ database, nếu được sửa đổi từ một thread khác, session khác
    . Còn trong điều kiện unit test, chạy trong cùng một thread, thì không cần lệnh merge
    */
    assertThat(bar.getName()).isEqualTo("New Foo");
    em.remove(bar);
    em.flush();
  }

  @Test
  void testDetach() {
    Bar bar  = new Bar();
    bar.setName("Foo");
    em.persist(bar);

    String id = bar.getId();
    Bar barInDB = em.find(Bar.class, id); //Query
    assertThat(barInDB.getName()).isEqualTo("Foo");
   
    em.detach(bar); //tách bar ra khỏi context
    bar.setName("New Foo");
    em.flush();
    barInDB = em.find(Bar.class, id);
    assertThat(barInDB).isNull();  //Không tìm được vì bar đã detach
  }

  @Test
  void testRepositoryCRUD() {
    Bar bar  = new Bar();
    bar.setName("Foo");

    barRepository.save(bar);
    String id = bar.getId();
    var foundBar = barRepository.findById(id).orElseThrow(()-> {
      return new RuntimeException("Bar is not found");      
    });

    assertThat(foundBar).isEqualTo(bar);
    barRepository.delete(foundBar);
    assertThat(barRepository.existsById(id)).isFalse();
  }

  @Test
  @Transactional
  void testInsertQuery() {   
    //Chú ý JPQL không hỗ trợ lệnh Insert 
    em.createNativeQuery("INSERT INTO bar (id, name) VALUES (?, ?)")
    .setParameter(1, RandomString.make(10))
    .setParameter(2, "Rock")
    .executeUpdate();
    em.flush();
  }

}

package vn.techmaster.demojpa;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import vn.techmaster.demojpa.relationship.onemany.orphanremove.Comment;
import vn.techmaster.demojpa.relationship.onemany.orphanremove.Post;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class OrphanRemovalTest {
  @Autowired private EntityManager em;

  @Test
  void testOrphanRemovalOneMany() {
    Post post = new Post("Ronaldo học lập trình");
    Comment comment1 = new Comment("Cậu này quá giỏi");
    Comment comment2 = new Comment("Tại sao không đá bóng nữa");
    post.addComment(comment1);
    post.addComment(comment2);
    em.persist(post);
    assertThat(post.getComments()).hasSize(2);
    long id1 = comment1.getId();
    long id2 = comment2.getId();

    em.remove(post);
    em.flush();
    Comment c1 = em.find(Comment.class, id1);
    Comment c2 = em.find(Comment.class, id2);

    assertThat(c1).isNull();
    assertThat(c2).isNull();
  }
}

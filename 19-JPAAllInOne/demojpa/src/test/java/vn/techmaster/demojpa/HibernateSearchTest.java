package vn.techmaster.demojpa;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import static org.assertj.core.api.Assertions.assertThat;
import vn.techmaster.demojpa.model.fulltextindex.Post;

@DataJpaTest
@Sql({ "/f_post.sql" })
public class HibernateSearchTest {
  @Autowired
  private EntityManager em;

  private FullTextEntityManager fullTextEntityManager;

  @BeforeEach
  void setUp() throws InterruptedException {
    fullTextEntityManager = Search.getFullTextEntityManager(em);
    fullTextEntityManager.createIndexer().startAndWait();
  }

  @Test
  void testKeywordSearch() throws InterruptedException {
    var queryBuilder = fullTextEntityManager.getSearchFactory()
        .buildQueryBuilder()
        .forEntity(Post.class)
        .get();

    Query query = queryBuilder
        .keyword()
        .onField("title")
        .matching("hèn kém")
        .createQuery();

    FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(query, Post.class);
    List<Post> results = jpaQuery.getResultList();
    assertThat(results).hasSizeGreaterThan(0);
  }

  @Test
  void testFuzzySearch() throws InterruptedException {
    var queryBuilder = fullTextEntityManager.getSearchFactory()
        .buildQueryBuilder()
        .forEntity(Post.class)
        .get();

    Query fuzzyQuery = queryBuilder
        .keyword()
        .fuzzy()
        .withEditDistanceUpTo(2)
        .withPrefixLength(2)
        .onField("title")
        .matching("forgot") // Từ nguyên gốc là forget
        .createQuery();

    FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(fuzzyQuery, Post.class);
    List<Post> results = jpaQuery.getResultList();
    assertThat(results).hasSizeGreaterThan(0);
  }
}

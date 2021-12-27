package vn.techmaster.demojpa;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import vn.techmaster.demojpa.relationship.onemany.bidirection.Department;
import vn.techmaster.demojpa.relationship.onemany.bidirection.Professor;
import vn.techmaster.demojpa.relationship.onemany.unidirection.Category;
import vn.techmaster.demojpa.relationship.onemany.unidirection.Product;

import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
public class OneManyTest {
  @Autowired private EntityManager em;
  
  @Test  @Transactional
  void testUniDirection() {
    Category homeappliance = new Category("Home Appliance");
    Product fridge = new Product("Fridge", homeappliance);
    //em.persist(homeappliance);
    em.persist(fridge);  
    assertThat(fridge.getCategory()).isEqualTo(homeappliance);
    em.flush();

    var  homeappliance_id = homeappliance.getId();
    em.remove(fridge);
    em.flush();
  }

  @Test  @Transactional
  void testBiDirection() {
    Department mathDept  = new  Department("Math");
    Professor  newton  = new Professor("New Ton");
    Professor  einstein  = new Professor("Einstein");
    mathDept.add(newton);
    mathDept.add(einstein);
    em.persist(mathDept);
    em.flush();
    assertThat(mathDept.getProfessors()).hasSize(2); //Có 2 giáo sư

    em.remove(newton);
    em.flush();
    assertThat(mathDept.getProfessors()).hasSize(1); //Newton mất khoa Toán chỉ còn 1 giáo sư

    em.remove(mathDept);
    assertThat(einstein.getDepartment()).isNull();

    Department physicsDept  = new  Department("Physics");
    physicsDept.add(einstein);
    assertThat(physicsDept.getProfessors()).hasSize(1);

    assertThat(einstein.getDepartment()).isEqualTo(physicsDept);
  }
}

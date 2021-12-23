package vn.techmaster.demojpa.controller;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.techmaster.demojpa.model.id.Bar;
import vn.techmaster.demojpa.repository.BarRepository;

@RestController
@RequestMapping("/api/demo")
public class CRUDController {
  @Autowired private EntityManager em;

  @Autowired private BarRepository barRepository;
  
  @GetMapping("/crudbar")
  @Transactional
  //http://localhost:8080/api/demo/crudbar
  public void crudBar() {
    Bar bar  = new Bar();
    bar.setName("Foo");
    em.persist(bar); //Create
    em.flush();
    String id = bar.getId();
    Bar barInDB = em.find(Bar.class, id); //Query
    barInDB.setName("New Foo"); //Update
    em.flush();
    em.merge(bar);  
    /* Cập nhật thay đổi từ database, nếu được sửa đổi từ một thread khác, session khác
    . Còn trong điều kiện unit test, chạy trong cùng một thread, thì không cần lệnh merge
    */
    em.remove(bar); //Delete
    em.flush();
  }

  @GetMapping("/crudbar2")
  @Transactional
  public void crudBar2() {
    Bar bar  = new Bar();
    bar.setName("Foo");
    barRepository.save(bar);
    String id = bar.getId();
    var foundBar = barRepository.findById(id).orElseThrow(()-> {
      return new RuntimeException("Bar is not found");      
    });
    barRepository.delete(foundBar);
  }
}

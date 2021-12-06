package vn.techmaster.relation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.techmaster.relation.model.inheritance.mappedsuperclass.BaseProduct;
import vn.techmaster.relation.model.inheritance.singletable.Electronics;
import vn.techmaster.relation.model.inheritance.tableperclass.Animal;
import vn.techmaster.relation.model.manymany.noextracolumns.Article;
import vn.techmaster.relation.model.manymany.separate_primary_key.Student;
import vn.techmaster.relation.model.onemany.bidirection.Post;
import vn.techmaster.relation.model.oneone.User;
import vn.techmaster.relation.model.selfreference.Employee;
import vn.techmaster.relation.model.selfreference.Person;
import vn.techmaster.relation.service.inheritance.mappedsuperclass.ProductService;
import vn.techmaster.relation.service.inheritance.singletable.EletronicsService;
import vn.techmaster.relation.service.inheritance.tableperclass.AnimalService;
import vn.techmaster.relation.service.manymany.ArticleTagService;
import vn.techmaster.relation.service.manymany.StudentSubjectService;
import vn.techmaster.relation.service.onemany.bidirection.PostService;
import vn.techmaster.relation.service.oneone.UserService;
import vn.techmaster.relation.service.selfreference.EmployeeService;
import vn.techmaster.relation.service.selfreference.FamilyService;

@RestController
public class APIController {
  @Autowired private UserService userService;
  
  @Autowired private StudentSubjectService studentSubjectService;
  
  @Autowired private ArticleTagService articleTagService;

  @Autowired private PostService postService;

  @Autowired private EmployeeService employeeService;

  @Autowired private FamilyService familyService;

  @Autowired private ProductService productService;  
  
  @Autowired private EletronicsService electronicsService;


  @Autowired private AnimalService animalService;

  @GetMapping("/user")
  public ResponseEntity<List<User>> getUsers() {
    return ResponseEntity.ok().body(userService.getAll());
  }

  @GetMapping("/users")
  public ResponseEntity<List<User>> queryAllUsers() {
    return ResponseEntity.ok().body(userService.queryAll());
  }


  @GetMapping("/student")
  public ResponseEntity<List<Student>> getStudents() {
    return ResponseEntity.ok().body(studentSubjectService.getAllStudents());
  }

  @GetMapping("/article")
  public ResponseEntity<List<Article>> getArticles() {
    return ResponseEntity.ok().body(articleTagService.getAllArticles());
  }

  @GetMapping("/post")
  public ResponseEntity<List<Post>> getPosts() {
    List<Post> result = postService.getAllPosts();
    return ResponseEntity.ok().body(result);
  }

  @GetMapping("/employee")
  public ResponseEntity<List<Employee>> getEmployees() {
    return ResponseEntity.ok().body(employeeService.getAllEmployees());
  }

  @GetMapping("/person")
  public ResponseEntity<List<Person>> getAllPeople() {
    return ResponseEntity.ok().body(familyService.getAll());
  }

  @GetMapping("/product")
  public ResponseEntity<List<BaseProduct>> getAllProducts() {
    return ResponseEntity.ok().body(productService.getAllProducts());
  }


  @GetMapping("/baseelectronics")
  public ResponseEntity<List<Electronics>> getPolymorphicElectronics() {
    return ResponseEntity.ok().body(electronicsService.getPolymorphicElectronics());
  }

  @GetMapping("/electronics")
  public ResponseEntity<List<Electronics>> getAllElectronics() {
    return ResponseEntity.ok().body(electronicsService.getAllElectronics());
  }

  @GetMapping("/animal")
  public ResponseEntity<List<Animal>> getAllAnimals() {
    return ResponseEntity.ok().body(animalService.getAllAnimals());
  }
}

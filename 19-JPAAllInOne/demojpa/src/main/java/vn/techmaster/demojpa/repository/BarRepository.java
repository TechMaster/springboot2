package vn.techmaster.demojpa.repository;

import org.springframework.data.repository.CrudRepository;

import vn.techmaster.demojpa.model.id.Bar;

public interface BarRepository extends CrudRepository<Bar, String> {
  
}

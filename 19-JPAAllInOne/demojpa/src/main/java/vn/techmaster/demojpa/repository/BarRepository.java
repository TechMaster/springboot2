package vn.techmaster.demojpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import vn.techmaster.demojpa.model.id.Bar;

public interface BarRepository extends JpaRepository<Bar, String> {
  
}

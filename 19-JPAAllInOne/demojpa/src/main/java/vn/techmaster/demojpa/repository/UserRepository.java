package vn.techmaster.demojpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.techmaster.demojpa.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
  
}

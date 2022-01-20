package vn.techmaster.fluxh2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.techmaster.fluxh2.model.Post;

public interface PostRepo extends JpaRepository<Post, Long> {
  
}

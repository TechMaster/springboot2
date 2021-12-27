package vn.techmaster.demojpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.techmaster.demojpa.relationship.onemany.unidirection.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
  
}

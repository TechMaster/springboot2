package vn.techmasterr.filmmock.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import vn.techmasterr.filmmock.model.Customer;

@Repository
public class CustomerRepoImpl implements CustomerRepo {
  
  @Override
  public Optional<Customer> findById(String id) {
    return null;
  }
  
}

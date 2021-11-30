package vn.techmasterr.filmmock.repository;

import java.util.Optional;

import vn.techmasterr.filmmock.model.Customer;

public interface CustomerRepo {
  Optional<Customer> findById(String id);
}

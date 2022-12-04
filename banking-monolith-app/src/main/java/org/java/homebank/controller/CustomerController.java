package org.java.homebank.controller;

import java.util.List;
import org.java.homebank.entity.Customer;
import org.java.homebank.exceptions.ResourceNotFoundException;
import org.java.homebank.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {


  private final CustomerRepository repository;

  public CustomerController(CustomerRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/customers")
  List<Customer> getAll() {
    return repository.findAll();
  }

  @PostMapping("/customers")
  ResponseEntity<Customer> createCustomer(
      @RequestBody
      Customer newCustomer) {
    return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(newCustomer));
  }

  @GetMapping("/customers/{id}")
  ResponseEntity<Customer> getOne(
      @PathVariable
      Long id) {
    final Customer customer = repository.findById(id).orElseThrow(ResourceNotFoundException::new);
    return ResponseEntity.ok(customer);
  }

  @PutMapping("/customers/{id}")
  ResponseEntity<Customer> updateCustomerName(
      @RequestBody
      Customer updatedCustomer,
      @PathVariable
      Long id) {
    final Customer customer = repository.findById(id).map(c -> {
      c.setName(updatedCustomer.getName());
      return repository.save(c);
    }).orElseThrow(ResourceNotFoundException::new);
    return ResponseEntity.ok(customer);
  }

  @DeleteMapping("/customers/{id}")
  void deleteCustomer(
      @PathVariable
      Long id) {
    repository.deleteById(id);
  }
}

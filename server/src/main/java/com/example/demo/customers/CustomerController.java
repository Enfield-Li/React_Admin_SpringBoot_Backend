package com.example.demo.customers;

import com.example.demo.customers.entity.Customer;
import com.example.demo.customers.repository.CustomerRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "customers")
@RequestMapping("customers")
public class CustomerController {

  @Autowired
  CustomerRepository customerRepository;

  @GetMapping("test")
  public void Test() {}

  @GetMapping
  public ResponseEntity<?> getAll() {
    return null;
  }

  @GetMapping(params = "id")
  public ResponseEntity<List<Customer>> getManyReference(
    @RequestParam("id") List<Long> id
  ) {
    System.out.println("customer size: " + id.size());

    return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(null);
  }

  @GetMapping("{id}")
  public ResponseEntity<?> getById(@PathVariable("id") Long id) {
    return null;
  }

  @PostMapping
  public void create(@RequestBody List<Customer> item) {
    customerRepository.saveAll(item);
  }

  @PutMapping("{id}")
  public ResponseEntity<?> update(
    @PathVariable("id") Long id,
    @RequestBody Customer item
  ) {
    return null;
  }

  @DeleteMapping("{id}")
  public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
    return null;
  }
}

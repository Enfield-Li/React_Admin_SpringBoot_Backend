package com.example.demo.customers;

import com.example.demo.customers.entity.Customer;
import com.example.demo.customers.repository.CustomerMapper;
import com.example.demo.customers.repository.CustomerRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.ArrayList;
import java.util.Date;
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

  CustomerRepository customerRepository;
  CustomerMapper customerMapper;

  @Autowired
  CustomerController(
    CustomerMapper customerMapper,
    CustomerRepository customerRepository
  ) {
    this.customerMapper = customerMapper;
    this.customerRepository = customerRepository;
  }

  @GetMapping("test")
  public void Test() {}

  /* 
    http://localhost:3060/customers?_end=25&_order=DESC&_sort=last_seen&_start=0
   */
  @GetMapping(params = { "_start", "_end", "_sort", "_order" })
  public ResponseEntity<List<Customer>> getAll(
    @RequestParam(name = "_start") Integer start,
    @RequestParam(name = "_end") Integer end,
    @RequestParam(name = "_sort") String sort,
    @RequestParam(name = "_order") String order,
    @RequestParam(name = "groups", required = false) String groups,
    @RequestParam(name = "last_seen_gte", required = false) Date last_seen_gte,
    @RequestParam(name = "has_newsletter", required = false) Boolean has_newsletter,
    @RequestParam(name = "nb_commands_gte", required = false) Integer nb_commands_gte
  ) {
    Integer take = end - start;

    List<Customer> paginatedCustomers = customerMapper.getPaginatedCustomers(
      start,
      take,
      sort,
      order,
      groups,
      has_newsletter,
      last_seen_gte,
      nb_commands_gte
    );

    String customerCount = customerMapper.getCustomerCount( groups,
      has_newsletter,
      last_seen_gte,
      nb_commands_gte);

    return ResponseEntity
      .ok()
      .header("X-Total-Count", customerCount)
      .body(paginatedCustomers);
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

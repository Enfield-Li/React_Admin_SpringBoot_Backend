package com.example.demo.customers;

import com.example.demo.customers.dto.CustomerDto;
import com.example.demo.customers.entity.Customer;
import com.example.demo.customers.repository.CustomerMapper;
import com.example.demo.customers.repository.CustomerRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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

  private static final String CUSTOMER_ID_STR = "customer_id";
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

  @PostMapping("test")
  public void Test(@RequestBody Customer item) {
    customerRepository.save(item);
  }

  /* 
    URL example:
    http://localhost:3060/customers?_end=25&_order=DESC&_sort=last_seen&_start=0
  
    * include last visited * 
    http://localhost:3060/customers?_end=25&_order=DESC&_sort=last_seen&_start=0&last_seen_gte=2022-06-01T15:59:59.999Z
    http://localhost:3060/customers?_end=25&_order=DESC&_sort=last_seen&_start=0&last_seen_gte=2022-04-30T16:00:00.000Z&last_seen_lte=2022-05-31T16:00:00.000Z
    http://localhost:3060/customers?_end=25&_order=DESC&_sort=last_seen&_start=0&last_seen_lte=2022-04-30T16:00:00.000Z
  
    * include has ordered *
    http://localhost:3060/customers?_end=25&_order=DESC&_sort=last_seen&_start=0&nb_commands_lte=0
    http://localhost:3060/customers?_end=25&_order=DESC&_sort=last_seen&_start=0&nb_commands_gte=1
  
    * include has newsletter *
    http://localhost:3060/customers?_end=25&_order=DESC&_sort=last_seen&_start=0&has_newsletter=false
    http://localhost:3060/customers?_end=25&_order=DESC&_sort=last_seen&_start=0&has_newsletter=true
  
    * include segment *
    http://localhost:3060/customers?_end=25&_order=DESC&_sort=last_seen&_start=0&groups=collector
   */
  @GetMapping(params = { "_start", "_end", "_sort", "_order" })
  public ResponseEntity<List<CustomerDto>> getAll(
    @RequestParam(name = "_start") Integer start,
    @RequestParam(name = "_end") Integer end,
    @RequestParam(name = "_sort") String sort,
    @RequestParam(name = "_order") String order,
    @RequestParam(name = "groups", required = false) String groups,
    @RequestParam(
      name = "last_seen_gte",
      required = false
    ) Instant last_seen_gte,
    @RequestParam(
      name = "last_seen_lte",
      required = false
    ) Instant last_seen_lte,
    @RequestParam(
      name = "has_newsletter",
      required = false
    ) Boolean has_newsletter,
    @RequestParam(
      name = "nb_commands_gte",
      required = false
    ) Integer nb_commands_gte,
    @RequestParam(
      name = "nb_commands_lte",
      required = false
    ) Integer nb_commands_lte
  ) {
    Integer take = end - start;
    sort = sort.equals(CUSTOMER_ID_STR) ? "id" : sort;

    List<CustomerDto> customerQueryResult = customerMapper.getCustomerQueryResult(
      start,
      take,
      sort,
      order,
      groups,
      has_newsletter,
      last_seen_gte,
      last_seen_lte,
      nb_commands_gte,
      nb_commands_lte
    );

    List<CustomerDto> customerList = buildCustomerList(customerQueryResult);

    String customerCount = customerMapper.getCustomerCount(
      groups,
      has_newsletter,
      last_seen_gte,
      last_seen_lte,
      nb_commands_gte,
      nb_commands_lte
    );

    return ResponseEntity
      .ok()
      .header("X-Total-Count", customerCount)
      .body(customerList);
  }

  @GetMapping(params = "id")
  public ResponseEntity<List<CustomerDto>> getManyReference(
    @RequestParam("id") List<Long> ids
  ) {
    List<CustomerDto> customers = customerMapper.getManyCustomers(ids);

    return ResponseEntity.ok().body(customers);
  }

  @GetMapping("{id}")
  public ResponseEntity<CustomerDto> getById(@PathVariable("id") Long id) {
    CustomerDto customer = customerMapper.getOneCustomer(id);

    return ResponseEntity.ok().body(processGroupData(customer));
  }

  @PostMapping
  public void create(@RequestBody List<Customer> item) {
    customerRepository.saveAll(item);
  }

  @PutMapping("{id}")
  public ResponseEntity<?> update(
    @PathVariable("id") Long id,
    @RequestBody CustomerDto item
  ) {
    return null;
  }

  @DeleteMapping("{id}")
  public ResponseEntity<HttpStatus> delete(@RequestParam("id") Long id) {
    System.out.println("delete customer by id: " + id);
    return null;
  }

  private List<CustomerDto> buildCustomerList(
    List<CustomerDto> customerQueryResult
  ) {
    return customerQueryResult
      .stream()
      .map(c -> processGroupData(c))
      .collect(Collectors.toList());
  }

  private CustomerDto processGroupData(CustomerDto customer) {
    if (customer.getGroupsStr() != null) {
      customer.setGroups(
        new ArrayList<String>(Arrays.asList(customer.getGroupsStr().split(",")))
      );
      customer.setGroupsStr(null);
    }

    return customer;
  }
}

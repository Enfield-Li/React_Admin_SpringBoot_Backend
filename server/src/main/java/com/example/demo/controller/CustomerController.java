package com.example.demo.controller;

import static com.example.demo.utils.Constants.*;

import com.example.demo.dto.CustomerDto;
import com.example.demo.entity.Customer;
import com.example.demo.mapper.CustomerMapper;
import com.example.demo.repository.CustomerRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
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
@RequestMapping(CUSTOMERS_ENDPOINT)
public class CustomerController {

  private final CustomerMapper customerMapper;
  private final CustomerRepository customerRepository;
  private static final String ID_STR = "id";
  private static final String GROUPS_STR = "groups";
  private static final String TOTAL_SPENT_STR = "total_spent";
  private static final String CUSTOMER_ID_STR = "customer_id";

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
    @RequestParam(name = "q", required = false) String queryByName,
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

    /* Defalut back to sort by "id" */
    sort = sort.equals(CUSTOMER_ID_STR) ? ID_STR : sort;
    sort = sort.equals(GROUPS_STR) ? TOTAL_SPENT_STR : sort;

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
      nb_commands_lte,
      queryByName
    );

    List<CustomerDto> customerList = buildCustomerList(customerQueryResult);

    String customerCount = customerMapper.getCustomerCount(
      groups,
      has_newsletter,
      last_seen_gte,
      last_seen_lte,
      nb_commands_gte,
      nb_commands_lte,
      queryByName
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
  public ResponseEntity<Customer> create(@RequestBody Customer item) {
    Customer customer = customerRepository.save(item);

    return ResponseEntity.ok().body(customer);
  }

  @PutMapping("{id}")
  public ResponseEntity<Customer> update(
    @PathVariable("id") Long id,
    @RequestBody Customer customer
  ) {
    customer.setId(id);
    Customer newCustomer = customerRepository.save(customer);

    return ResponseEntity.ok().body(newCustomer);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
    customerRepository.deleteById(id);
    return ResponseEntity.ok().body(true);
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
      customer.setGroups(Arrays.asList(customer.getGroupsStr().split(",")));
    }

    customer.setGroupsStr(null);
    return customer;
  }
}

package com.example.demo.customers.repository;

import com.example.demo.customers.entity.Customer;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CustomerMapper {
  public List<Customer> getPaginatedCustomers(
    @Param("start") Integer start,
    @Param("take") Integer take,
    @Param("sort") String sort,
    @Param("order") String order,
    @Param("groups") String groups,
    @Param("has_newsletter") Boolean has_newsletter,
    @Param("last_seen_gte") Date last_seen_gte,
    @Param("nb_commands_gte") Integer nb_commands_gte
  );

  public String getCustomerCount(
    @Param("groups") String groups,
    @Param("has_newsletter") Boolean has_newsletter,
    @Param("last_seen_gte") Date last_seen_gte,
    @Param("nb_commands_gte") Integer nb_commands_gte
  );
}

package com.example.demo.customers.repository;

import com.example.demo.customers.dto.CustomerDto;
import java.time.Instant;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CustomerMapper {
  @Select(
    "SELECT c.*, GROUP_CONCAT(DISTINCT cg.group_type_name) as 'groupsStr'" +
    " FROM customer c" +
    " LEFT JOIN customer_group cg ON cg.customer_id = c.id" +
    " WHERE c.id = #{id}" + // id
    " GROUP BY c.id"
  )
  public CustomerDto getOneCustomer(@Param("id") Long id);

  public List<CustomerDto> getCustomerQueryResult(
    @Param("start") Integer start,
    @Param("take") Integer take,
    @Param("sort") String sort,
    @Param("order") String order,
    @Param("groups") String groups,
    @Param("has_newsletter") Boolean has_newsletter,
    @Param("last_seen_gte") Instant last_seen_gte,
    @Param("last_seen_lte") Instant last_seen_lte,
    @Param("nb_commands_gte") Integer nb_commands_gte,
    @Param("nb_commands_lte") Integer nb_commands_lte,
    @Param("customerName") String customerName
  );

  public String getCustomerCount(
    @Param("groups") String groups,
    @Param("has_newsletter") Boolean has_newsletter,
    @Param("last_seen_gte") Instant last_seen_gte,
    @Param("last_seen_lte") Instant last_seen_lte,
    @Param("nb_commands_gte") Integer nb_commands_gte,
    @Param("nb_commands_lte") Integer nb_commands_lte,
    @Param("customerName") String customerName
  );

  public List<CustomerDto> getManyCustomers(List<Long> ids);
}

package com.example.demo.customers.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "group_name")
public class Group {

  @Id
  private String customer_type;
}

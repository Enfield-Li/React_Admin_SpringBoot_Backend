package com.example.demo.customers.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "group_name")
public class Group {

  @Id
  private String customer_type;
}

package com.example.demo.products.entity;

import static javax.persistence.GenerationType.AUTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Category {

  @Id
  @GeneratedValue(strategy = AUTO)
  private Long id;

  private String name;

  @OneToOne(mappedBy = "category_id")
  private Product product;
}

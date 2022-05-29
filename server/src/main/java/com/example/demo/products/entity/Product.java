package com.example.demo.products.entity;

import static javax.persistence.GenerationType.AUTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product {

  @Id
  @GeneratedValue(strategy = AUTO)
  private Long id;

  @Column(unique = true)
  private String reference;

  @Column(columnDefinition = "TEXT")
  private String description;

  private Float width;
  private Float height;
  private String image;
  private String thumbnail;

  private Float price;
  private Integer sales;
  private Integer stock;
  private Integer category_id;
}

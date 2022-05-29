package com.example.demo.products.entity;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.AUTO;

import com.example.demo.reviews.entity.Review;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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

  private Float width;
  private Float height;

  private Float price;
  private Integer sales;
  private Integer stock;

  private String reference;
  private String thumbnail;
  private String image;
  private String description;

  @OneToOne
  @JoinColumn(referencedColumnName = "id", name = "category_id")
  private Category category_id;

  @OneToMany(
    mappedBy = "customer",
    cascade = ALL,
    orphanRemoval = true,
    fetch = LAZY
  )
  private List<Review> reviews = new ArrayList<>();
}

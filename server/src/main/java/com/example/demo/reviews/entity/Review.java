package com.example.demo.reviews.entity;

import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.GenerationType.IDENTITY;

import com.example.demo.commands.entity.Command;
import com.example.demo.customers.entity.Customer;
import com.example.demo.products.entity.Product;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Review {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @CreationTimestamp
  private Instant date;

  @Column(columnDefinition = "TEXT")
  private String comment;

  private String status;
  private Integer rating;

  @Column(insertable = false, updatable = false)
  private Long customer_id;

  @Column(insertable = false, updatable = false)
  private Long product_id;

  @Column(insertable = false, updatable = false)
  private Long command_id;

  @ManyToOne(cascade = DETACH)
  @JoinColumn(name = "customer_id")
  private Customer customer;

  @ManyToOne(cascade = DETACH)
  @JoinColumn(name = "product_id")
  private Product product;

  @ManyToOne(cascade = DETACH)
  @JoinColumn(name = "command_id")
  private Command command;
}

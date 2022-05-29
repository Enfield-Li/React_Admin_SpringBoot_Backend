package com.example.demo.reviews.entity;

import com.example.demo.commands.entity.Command;
import com.example.demo.customers.entity.Customer;
import com.example.demo.products.entity.Product;
import java.time.Instant;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @CreationTimestamp
  private Instant date;

  private String status;
  private Integer rating;
  private String comment;

  @ManyToOne
  @JoinColumn(name = "customer_id")
  private Customer customer;

  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;

  @ManyToOne
  @JoinColumn(name = "command_id")
  private Command command;
}

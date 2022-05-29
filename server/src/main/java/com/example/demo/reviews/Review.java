package com.example.demo.reviews;

import com.example.demo.commands.Command;
import com.example.demo.customers.entity.Customer;
import com.example.demo.products.entity.Product;
import java.time.Instant;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity
public class Review {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @CreationTimestamp
  private Instant date;

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

  @OneToOne
  @JoinColumn(referencedColumnName = "status", name = "status")
  private ReviewStats status;
}

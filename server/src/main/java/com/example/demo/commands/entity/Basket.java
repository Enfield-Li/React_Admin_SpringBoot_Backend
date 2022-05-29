package com.example.demo.commands.entity;

import com.example.demo.products.entity.Product;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Basket {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Integer quantity;

  private Long product_id;

  @ManyToOne
  private Command command_id;
}

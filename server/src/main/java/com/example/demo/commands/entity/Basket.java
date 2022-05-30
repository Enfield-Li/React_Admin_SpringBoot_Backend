package com.example.demo.commands.entity;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.AUTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Basket {

  @Id
  @GeneratedValue(strategy = AUTO)
  private Long id;

  private Integer quantity;
  private Long product_id;

  @Column(updatable = false, insertable = false, nullable = false)
  private Long command_id;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "command_id")
  private Command command;

  public Basket() {}

  private Basket(Integer quantity, Long product_id, Command command) {
    this.command = command;
    this.quantity = quantity;
    this.product_id = product_id;
  }

  public static Basket of(Integer quantity, Long product_id, Command command) {
    return new Basket(quantity, product_id, command);
  }
}

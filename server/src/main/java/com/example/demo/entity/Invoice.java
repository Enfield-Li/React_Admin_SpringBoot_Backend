package com.example.demo.entity;

import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.GenerationType.IDENTITY;

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
public class Invoice {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @CreationTimestamp
  private Instant date;

  private Float total_ex_taxes;
  private Float delivery_fees;
  private Float tax_rate;
  private Float taxes;
  private Float total;

  @Column(insertable = false, updatable = false)
  private Long command_id;

  @Column(insertable = false, updatable = false)
  private Long customer_id;

  @ManyToOne(cascade = DETACH)
  @JoinColumn(name = "customer_id")
  private Customer customer;

  @ManyToOne(cascade = DETACH)
  @JoinColumn(name = "command_id")
  private Command command;
}

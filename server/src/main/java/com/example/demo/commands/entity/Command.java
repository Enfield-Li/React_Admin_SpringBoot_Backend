package com.example.demo.commands.entity;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.GenerationType.IDENTITY;

import com.example.demo.customers.entity.Customer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity
public class Command {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @CreationTimestamp
  private Instant date;

  private String status;
  private String reference;
  private Float total_ex_taxes;
  private Float delivery_fees;
  private Float tax_rate;
  private Float taxes;
  private Float total;
  private Boolean returned;

  @Column(insertable = false, updatable = false)
  private Long customer_id;

  @ManyToOne(cascade = DETACH)
  @JoinColumn(name = "customer_id")
  private Customer customer;

  @OneToMany(
    mappedBy = "command",
    cascade = ALL,
    orphanRemoval = true,
    targetEntity = Basket.class,
    fetch = FetchType.EAGER
  )
  @JsonIgnoreProperties("command")
  private List<Basket> basket = new ArrayList<>();
}

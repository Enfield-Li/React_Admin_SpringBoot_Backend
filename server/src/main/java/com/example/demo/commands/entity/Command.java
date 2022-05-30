package com.example.demo.commands.entity;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.AUTO;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity
public class Command {

  @Id
  @GeneratedValue(strategy = AUTO)
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
  private Long customer_id;

  @OneToMany(
    mappedBy = "command",
    cascade = ALL,
    orphanRemoval = true,
    targetEntity = Basket.class,
    fetch = LAZY
  )
  private List<Basket> basket = new ArrayList<>();
}

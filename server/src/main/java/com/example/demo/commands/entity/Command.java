package com.example.demo.commands.entity;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
  private Long customer_id;

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

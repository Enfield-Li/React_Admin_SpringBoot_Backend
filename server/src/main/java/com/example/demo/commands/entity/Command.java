package com.example.demo.commands.entity;

import static javax.persistence.GenerationType.AUTO;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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

  @OneToMany
  @JoinTable(
    name = "command_basket",
    joinColumns = @JoinColumn(name = "command_id"),
    inverseJoinColumns = @JoinColumn(name = "basket_id")
  )
  private List<Basket> basket = new ArrayList<>();
}

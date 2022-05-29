package com.example.demo.invoices.entity;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.AUTO;

import com.example.demo.commands.entity.Command;
import com.example.demo.customers.entity.Customer;
import java.time.Instant;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity
public class Invoice {

  @Id
  @GeneratedValue(strategy = AUTO)
  private Long id;

  @CreationTimestamp
  private Instant date;

  private Float total_ex_taxes;
  private Float delivery_fees;
  private Float tax_rate;
  private Float taxes;
  private Float total;

  @OneToOne
  @JoinColumn(name = "command_id")
  private Command command;

  @ManyToOne(fetch = LAZY)
  private Customer customer;
}

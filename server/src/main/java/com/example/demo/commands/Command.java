package com.example.demo.commands;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.AUTO;

import com.example.demo.customers.entity.Customer;
import com.example.demo.invoices.Invoice;
import com.example.demo.reviews.Review;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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

  private String reference;
  private Float total_ex_taxes;
  private Float delivery_fees;
  private Float tax_rate;
  private Float taxes;
  private Float total;
  private Boolean returned;

  @OneToOne
  @JoinColumn(referencedColumnName = "status", name = "status")
  private DeliveryStats status;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "customer_id")
  private Customer customer;

  @OneToMany
  @JoinTable(
    name = "command_basket",
    joinColumns = @JoinColumn(name = "command_id"),
    inverseJoinColumns = @JoinColumn(name = "basket_id")
  )
  private List<Basket> basket = new ArrayList<>();

  @OneToOne(mappedBy = "command")
  private Invoice invoice;

  @OneToMany(
    mappedBy = "customer",
    cascade = ALL,
    orphanRemoval = true,
    fetch = LAZY
  )
  private List<Review> reviews;
}

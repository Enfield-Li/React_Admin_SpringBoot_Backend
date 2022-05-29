package com.example.demo.customers.entity;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.AUTO;

import com.example.demo.commands.entity.Command;
import com.example.demo.invoices.entity.Invoice;
import com.example.demo.reviews.entity.Review;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

  @Id
  @GeneratedValue(strategy = AUTO)
  private Long id;

  @Column(unique = true, nullable = false)
  private String email;

  @Column(nullable = false)
  private String first_name;

  @Column(nullable = false)
  private String last_name;

  private String address;
  private String zipcode;
  private String city;

  private String avatar;
  private String stateAbbr;
  private Instant birthday;
  private Instant first_seen;
  private Instant last_seen;
  private Instant latest_purchase;

  private Boolean has_ordered;
  private Boolean has_newsletter;
  private Integer nb_commands;
  private Float total_spent;

  @ManyToMany
  @JoinTable(
    name = "customer_group",
    joinColumns = @JoinColumn(name = "customer_id"),
    inverseJoinColumns = @JoinColumn(name = "group_type_name")
  )
  private List<Group> groups = new ArrayList<>();

  @OneToMany(
    mappedBy = "customer",
    cascade = ALL,
    orphanRemoval = true,
    fetch = LAZY
  )
  private List<Command> commands = new ArrayList<>();

  @OneToMany(
    mappedBy = "customer",
    cascade = ALL,
    orphanRemoval = true,
    fetch = LAZY
  )
  private List<Invoice> invoices = new ArrayList<>();

  @OneToMany(
    mappedBy = "customer",
    cascade = ALL,
    orphanRemoval = true,
    fetch = LAZY
  )
  private List<Review> reviews = new ArrayList<>();
}

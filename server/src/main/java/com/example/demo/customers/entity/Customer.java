package com.example.demo.customers.entity;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.AUTO;

import com.example.demo.commands.Command;
import com.example.demo.invoices.Invoice;
import com.example.demo.reviews.Review;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Customer {

  @Id
  @GeneratedValue(strategy = AUTO)
  private Long id;

  private String first_name;
  private String last_name;
  private String email;
  private String address;
  private String zipcode;
  private String city;
  private String birthday;

  private String avatar;
  private String stateAbbr;
  private String first_seen;
  private String last_seen;
  private String latest_purchase;
  private Float total_spent;

  private Boolean has_ordered;
  private Boolean has_newsletter;
  private Integer nb_commands;

  @OneToMany
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
  private List<Invoice> invoices;

  @OneToMany(
    mappedBy = "customer",
    cascade = ALL,
    orphanRemoval = true,
    fetch = LAZY
  )
  private List<Review> reviews;
}

package com.example.demo.reviews.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Review {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @CreationTimestamp
  private Instant date;

  @Column(columnDefinition = "TEXT")
  private String comment;

  private String status;
  private Integer rating;

  private Long customer_id;
  private Long product_id;
  private Long command_id;
}

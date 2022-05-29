package com.example.demo.reviews;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class ReviewStats {

  @Id
  private String status;
}

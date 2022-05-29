package com.example.demo.commands;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class DeliveryStats {

  @Id
  private String status;
}

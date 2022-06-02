package com.example.demo.commands.dto;

import com.example.demo.commands.entity.Basket;

import lombok.Data;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
public class CommandDto {

  private Long id;
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
  private String productId_quantity;
  private List<BasketDto> basket = new ArrayList<>();
}

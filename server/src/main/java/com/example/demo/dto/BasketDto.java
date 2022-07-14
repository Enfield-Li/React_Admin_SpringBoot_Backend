package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasketDto {

  private Integer quantity;
  private Long product_id;

  public static BasketDto of(Integer quantity, Long product_id) {
    return new BasketDto(quantity, product_id);
  }
}

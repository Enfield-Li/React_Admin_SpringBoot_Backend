package com.example.demo.products.repository;

import com.example.demo.products.entity.Product;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ProductMapper {
  @Select("SELECT * FROM product LIMIT #{end}")
  public List<Product> getPaginatedProducts(@Param("end") Integer end);
}

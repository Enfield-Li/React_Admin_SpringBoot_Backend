package com.example.demo.products.repository;

import com.example.demo.products.entity.Product;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ProductMapper {
  @Select(
    "SELECT * FROM product ORDER BY #{sort} #{order}" +
    " LIMIT #{take} OFFSET #{start}"
  )
  public List<Product> getPaginatedProducts(
    @Param("start") Integer start,
    @Param("take") Integer take,
    @Param("sort") String sort,
    @Param("order") String order
  );

  @Select("SELECT count(*) AS count from product")
  public String getProductCount();
}

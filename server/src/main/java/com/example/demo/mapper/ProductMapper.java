package com.example.demo.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.entity.Product;

@Mapper
public interface ProductMapper {
  public List<Product> getPaginatedProducts(
    @Param("start") Integer start,
    @Param("take") Integer take,
    @Param("sort") String sort,
    @Param("order") String order,
    @Param("category_id") Long category_id,
    @Param("sales_gt") Integer sales_gt,
    @Param("sales_lte") Integer sales_lte,
    @Param("stock_gt") Integer stock_gt,
    @Param("stock_lt") Integer stock_lt,
    @Param("searchText") String searchText
  );

  public String getProductCount(
    @Param("category_id") Long category_id,
    @Param("sales_gt") Integer sales_gt,
    @Param("sales_lte") Integer sales_lte,
    @Param("stock_gt") Integer stock_gt,
    @Param("stock_lt") Integer stock_lt,
    @Param("searchText") String searchText
  );

  public List<Product> getManyProducts(List<Long> ids);
}

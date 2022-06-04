package com.example.demo.products.repository;

import com.example.demo.products.entity.Product;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

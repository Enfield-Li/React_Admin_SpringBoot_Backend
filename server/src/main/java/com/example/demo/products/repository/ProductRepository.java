package com.example.demo.products.repository;

import com.example.demo.products.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
  @Query(value = "SELECT * FROM product LIMIT #{end}", nativeQuery = true)
  List<Product> getPaginatedProducts(
    // @Param("sort") String sort,
    // @Param("order") String order,
    // @Param("start") String start,
    @Param("end") Integer end
  );
}

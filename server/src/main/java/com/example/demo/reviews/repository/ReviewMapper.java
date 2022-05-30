package com.example.demo.reviews.repository;

import com.example.demo.reviews.entity.Review;
import java.time.Instant;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ReviewMapper {
  @Select("SELECT * FROM review WHERE product_id = #{productId}")
  public List<Review> getUserPostWithInteractions(
    @Param("productId") Long productId
  );
}

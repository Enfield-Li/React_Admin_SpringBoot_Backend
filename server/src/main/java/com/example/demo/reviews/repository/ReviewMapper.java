package com.example.demo.reviews.repository;

import com.example.demo.reviews.entity.Review;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ReviewMapper {
  @Select(
    "SELECT * FROM review ORDER BY #{sort} #{order}" +
    " LIMIT #{take} OFFSET #{start}"
  )
  public List<Review> getPaginatedReviews(
    @Param("start") Integer start,
    @Param("take") Integer take,
    @Param("sort") String sort,
    @Param("order") String order
  );
}

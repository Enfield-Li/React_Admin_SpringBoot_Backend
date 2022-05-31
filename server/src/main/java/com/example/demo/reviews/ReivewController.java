package com.example.demo.reviews;

import com.example.demo.reviews.entity.Review;
import com.example.demo.reviews.repository.ReviewMapper;
import com.example.demo.reviews.repository.ReviewRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Review")
@RequestMapping("/reviews")
class ReviewController {

  @Autowired
  ReviewRepository repository;

  @Autowired
  ReviewMapper reviewMapper;

  @GetMapping("test")
  public void Test() {}

  @GetMapping(params = { "_start", "_end", "_sort", "_order", "product_id" })
  public ResponseEntity<List<Review>> getReviewForProduct(
    @RequestParam(name = "_start", required = false) Integer start,
    @RequestParam(name = "_end", required = false) Integer end,
    @RequestParam(name = "_sort", required = false) String sort,
    @RequestParam(name = "_order", required = false) String order,
    @RequestParam(name = "product_id", required = false) Long productId
  ) {
    Integer take = end - start;

    List<Review> reviewsForProduct = reviewMapper.getPaginatedReviews(
      start,
      take,
      sort,
      order,
      productId
    );

    String reviewCount = reviewMapper.getReviewCount(productId);

    return ResponseEntity
      .ok()
      .header("X-Total-Count", reviewCount)
      .body(reviewsForProduct);
  }

  @GetMapping(params = { "_start", "_end", "_sort", "_order" })
  public ResponseEntity<List<Review>> getAllReviews(
    @RequestParam(name = "_start", required = false) Integer start,
    @RequestParam(name = "_end", required = false) Integer end,
    @RequestParam(name = "_sort", required = false) String sort,
    @RequestParam(name = "_order", required = false) String order
  ) {
    Integer take = end - start;

    List<Review> reviewsForProduct = reviewMapper.getPaginatedReviews(
      start,
      take,
      sort,
      order,
      null
    );

    String reviewCount = reviewMapper.getReviewCount(null);

    return ResponseEntity
      .ok()
      .header("X-Total-Count", reviewCount)
      .body(reviewsForProduct);
  }

  @GetMapping("{id}")
  public ResponseEntity<Review> getById(@PathVariable("id") Long id) {
    return null;
  }

  @PostMapping
  public ResponseEntity<Review> create(@RequestBody List<Review> item) {
    repository.saveAll(item);
    return null;
  }

  @PutMapping("{id}")
  public ResponseEntity<Review> update(
    @PathVariable("id") Long id,
    @RequestBody Review item
  ) {
    return null;
  }

  @DeleteMapping("{id}")
  public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
    return null;
  }
}

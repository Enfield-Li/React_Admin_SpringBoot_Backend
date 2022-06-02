package com.example.demo.reviews;

import com.example.demo.reviews.entity.Review;
import com.example.demo.reviews.repository.ReviewMapper;
import com.example.demo.reviews.repository.ReviewRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.NoSuchElementException;
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

  @GetMapping(params = { "_start", "_end", "_sort", "_order", "customer_id" })
  public ResponseEntity<List<Review>> getReviewForCustomer(
    @RequestParam(name = "_start") Integer start,
    @RequestParam(name = "_end") Integer end,
    @RequestParam(name = "_sort") String sort,
    @RequestParam(name = "_order") String order,
    @RequestParam(name = "customer_id", required = false) Long customer_id
  ) {
    Integer take = end - start;

    List<Review> reviewsForProduct = reviewMapper.getPaginatedReviews(
      start,
      take,
      sort,
      order,
      null,
      null,
      customer_id
    );
    if (reviewsForProduct.size() > 0) System.out.println(
      reviewsForProduct.get(0).toString()
    );

    return ResponseEntity.ok().body(reviewsForProduct);
  }

  @GetMapping(params = { "_start", "_end", "_sort", "_order", "product_id" })
  public ResponseEntity<List<Review>> getReviewForProduct(
    @RequestParam(name = "_start") Integer start,
    @RequestParam(name = "_end") Integer end,
    @RequestParam(name = "_sort") String sort,
    @RequestParam(name = "_order") String order,
    @RequestParam(name = "product_id", required = false) Long product_id
  ) {
    Integer take = end - start;

    List<Review> reviewsForProduct = reviewMapper.getPaginatedReviews(
      start,
      take,
      sort,
      order,
      null,
      product_id,
      null
    );

    String reviewCount = reviewMapper.getReviewCount(product_id, null);

    return ResponseEntity
      .ok()
      .header("X-Total-Count", reviewCount)
      .body(reviewsForProduct);
  }

  @GetMapping(params = { "_start", "_end", "_sort", "_order" })
  public ResponseEntity<List<Review>> getAllReviews(
    @RequestParam(name = "_start") Integer start,
    @RequestParam(name = "_end") Integer end,
    @RequestParam(name = "_sort") String sort,
    @RequestParam(name = "_order") String order,
    @RequestParam(name = "status") String status
  ) {
    Integer take = end - start;

    List<Review> allReviews = reviewMapper.getPaginatedReviews(
      start,
      take,
      sort,
      order,
      status,
      null,
      null
    );

    String reviewCount = reviewMapper.getReviewCount(null, status);

    return ResponseEntity
      .ok()
      .header("X-Total-Count", reviewCount)
      .body(allReviews);
  }

  @GetMapping("{id}")
  public ResponseEntity<Review> getById(@PathVariable("id") Long id) {
    Review review = repository
      .findById(id)
      .orElseThrow(NoSuchElementException::new);

    return ResponseEntity.ok().body(review);
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

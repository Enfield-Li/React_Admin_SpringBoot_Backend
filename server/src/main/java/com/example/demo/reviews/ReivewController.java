package com.example.demo.reviews;

import com.example.demo.reviews.dto.UpdateReviewStatusDto;
import com.example.demo.reviews.entity.Review;
import com.example.demo.reviews.repository.ReviewMapper;
import com.example.demo.reviews.repository.ReviewRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "http://localhost:3000")
class ReviewController {

  private final ReviewRepository reviewRepository;
  private final ReviewMapper reviewMapper;

  @Autowired
  ReviewController(
    ReviewRepository reviewRepository,
    ReviewMapper reviewMapper
  ) {
    this.reviewRepository = reviewRepository;
    this.reviewMapper = reviewMapper;
  }

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

    String reviewCount = reviewMapper.getReviewCount(null, null, customer_id);

    return ResponseEntity
      .ok()
      .header("X-Total-Count", reviewCount)
      .body(reviewsForProduct);
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

    String reviewCount = reviewMapper.getReviewCount(product_id, null, null);

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
    @RequestParam(name = "status", required = false) String status
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

    String reviewCount = reviewMapper.getReviewCount(null, status, null);

    return ResponseEntity
      .ok()
      .header("X-Total-Count", reviewCount)
      .body(allReviews);
  }

  @GetMapping("{id}")
  public ResponseEntity<Review> getById(@PathVariable("id") Long id) {
    Review review = reviewRepository
      .findById(id)
      .orElseThrow(NoSuchElementException::new);

    return ResponseEntity.ok().body(review);
  }

  @PostMapping
  public ResponseEntity<Review> create(@RequestBody List<Review> item) {
    reviewRepository.saveAll(item);
    return null;
  }

  @PutMapping("{id}")
  public ResponseEntity<Boolean> update(
    @PathVariable("id") Long id,
    @RequestBody UpdateReviewStatusDto dto
  ) {
    String newStatus = dto.getStatus();
    Integer updateResult = reviewMapper.updateReviewStatus(id, newStatus);

    return ResponseEntity.ok().body(updateResult > 0);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
    System.out.println(id);
    return null;
  }
}

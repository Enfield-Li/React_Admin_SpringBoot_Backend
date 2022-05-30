package com.example.demo.reviews;

import com.example.demo.reviews.entity.Review;
import com.example.demo.reviews.repository.ReviewRepository;
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
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Review")
@RequestMapping("/review")
class ReviewController {

  @Autowired
  ReviewRepository repository;

  @GetMapping("test")
  public void Test() {}

  @GetMapping
  public ResponseEntity<List<Review>> getAll() {
    return null;
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

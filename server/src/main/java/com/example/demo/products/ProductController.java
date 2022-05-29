package com.example.demo.products;

import com.example.demo.products.entity.Product;
import com.example.demo.products.repository.ProductRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@RestController
@Tag(name = "products")
@RequestMapping("product")
class ProductController {

  @Autowired
  ProductRepository productRepository;

  @PutMapping("test")
  public Product Test(@RequestBody Product item) {
    System.out.println(item.toString());
    productRepository.save(item);
    return item;
  }

  @GetMapping
  public ResponseEntity<?> getAll() {
    return null;
  }

  @GetMapping("{id}")
  public ResponseEntity<?> getById(@PathVariable("id") Long id) {
    return null;
  }

  @PostMapping
  public ResponseEntity<?> create(@RequestBody List<Product> item) {
    for (Product i : item) {
      productRepository.save(i);
    }

    return null;
  }

  @PutMapping("{id}")
  public ResponseEntity<?> update(
    @PathVariable("id") Long id,
    @RequestBody Product item
  ) {
    return null;
  }

  @DeleteMapping("{id}")
  public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
    return null;
  }
}

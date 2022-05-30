package com.example.demo.products;

import com.example.demo.products.entity.Product;
import com.example.demo.products.repository.ProductRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
@Tag(name = "products")
@RequestMapping("products")
class ProductController {

  @Autowired
  ProductRepository productRepository;

  @PutMapping("test")
  public void Test() {}

  @GetMapping
  public ResponseEntity<?> getAll(
    @RequestParam(name = "_end", required = false) Integer end,
    @RequestParam(name = "_start", required = false) String start,
    @RequestParam(name = "_order", required = false) String order,
    @RequestParam(name = "_sort", required = false) String sort
  ) {
    List<Product> products = productRepository.findAll();

    HttpHeaders header = new HttpHeaders();
    header.add("X-Total-Count", "129");

    // return ResponseEntity.ok().header("X-Total-Count", "129").body(products);
    return ResponseEntity.ok().headers(header).body(products);
  }

  @GetMapping("{id}")
  public ResponseEntity<?> getById(@PathVariable("id") Long id) {
    return null;
  }

  @PostMapping
  public ResponseEntity<?> create(@RequestBody List<Product> item) {
    productRepository.saveAll(item);
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

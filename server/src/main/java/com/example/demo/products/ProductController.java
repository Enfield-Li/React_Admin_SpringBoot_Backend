package com.example.demo.products;

import com.example.demo.products.entity.Product;
import com.example.demo.products.repository.ProductMapper;
import com.example.demo.products.repository.ProductRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
@Tag(name = "products")
@RequestMapping("products")
@CrossOrigin(origins = "http://localhost:3000")
class ProductController {

  @Autowired
  ProductRepository productRepository;

  @Autowired
  ProductMapper prouctMapper;

  @PutMapping("test")
  public void Test() {}

  @PostMapping("bulk")
  public ResponseEntity<?> bulk(@RequestBody List<Product> item) {
    productRepository.saveAll(item);
    return null;
  }

  @GetMapping
  public ResponseEntity<?> getAll(
    @RequestParam(name = "_start", required = false) Integer start,
    @RequestParam(name = "_end", required = false) Integer end,
    @RequestParam(name = "_sort", required = false) String sort,
    @RequestParam(name = "_order", required = false) String order,
    @RequestParam(name = "category_id", required = false) Long category_id,
    @RequestParam(name = "sales_gt", required = false) Integer sales_gt,
    @RequestParam(name = "sales_lte", required = false) Integer sales_lte,
    @RequestParam(name = "stock_gt", required = false) Integer stock_gt,
    @RequestParam(name = "stock_lt", required = false) Integer stock_lt
  ) {
    Integer take = end - start;

    List<Product> products = prouctMapper.getPaginatedProducts(
      start,
      take,
      sort,
      order
    );

    String productCount = prouctMapper.getProductCount();

    return ResponseEntity
      .ok()
      .header("X-Total-Count", productCount)
      .body(products);
  }

  @GetMapping("{id}")
  public ResponseEntity<?> getById(@PathVariable("id") Long id) {
    Product product = productRepository
      .findById(id)
      .orElseThrow(NoSuchElementException::new);

    return ResponseEntity.ok().body(product);
  }

  @PostMapping
  public ResponseEntity<?> create(@RequestBody Product item) {
    Product createdProduct = productRepository.save(item);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
  }

  @Transactional
  @PutMapping("{id}")
  public ResponseEntity<?> update(
    @PathVariable("id") Long id,
    @RequestBody Product item
  ) {
    Product product = productRepository
      .findById(id)
      .orElseThrow(NoSuchElementException::new);

    productRepository.deleteById(id);

    return ResponseEntity.ok().body(product);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<?> delete(@PathVariable("id") Long id) {
    Product product = productRepository
      .findById(id)
      .orElseThrow(NoSuchElementException::new);

    productRepository.deleteById(id);

    return ResponseEntity.ok().body(product);
  }
}

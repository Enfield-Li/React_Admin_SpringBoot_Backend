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
// @CrossOrigin(origins = "http://localhost:3000")
class ProductController {

  ProductRepository productRepository;
  ProductMapper productMapper;

  @Autowired
  ProductController(
    ProductRepository productRepository,
    ProductMapper prouctMapper
  ) {
    this.productRepository = productRepository;
    this.productMapper = prouctMapper;
  }

  @PutMapping("test")
  public void Test() {}

  @PostMapping("bulk")
  public ResponseEntity<?> bulk(@RequestBody List<Product> item) {
    productRepository.saveAll(item);
    return null;
  }

  /* 
    URL example: 
    http://localhost:3060/products?_end=24&_order=ASC&_sort=stock&_start=0
    http://localhost:3060/products?_end=24&_order=ASC&_sort=stock&_start=0&sales_gt=10&sales_lte=25
    http://localhost:3060/products?_end=24&_order=ASC&_sort=stock&_start=0&sales_gt=10&sales_lte=25&stock_gt=0&stock_lt=10
    http://localhost:3060/products?_end=24&_order=ASC&_sort=stock&_start=0&category_id=2&sales_gt=10&sales_lte=25&stock_gt=0&stock_lt=10
  */
  @GetMapping(params = { "_start", "_end", "_sort", "_order" })
  public ResponseEntity<List<Product>> getAll(
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

    List<Product> products = productMapper.getPaginatedProducts(
      start,
      take,
      sort,
      order,
      category_id,
      sales_gt,
      sales_lte,
      stock_gt,
      stock_lt
    );

    String productCount = productMapper.getProductCount(
      category_id,
      sales_gt,
      sales_lte,
      stock_gt,
      stock_lt
    );

    return ResponseEntity
      .ok()
      .header("X-Total-Count", productCount)
      .body(products);
  }

  @GetMapping(params = "id")
  public ResponseEntity<List<Product>> getManyReference(
    @RequestParam("id") List<Long> id
  ) {
    System.out.println("product size: " + id.size());

    return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(null);
  }

  @GetMapping("{id}")
  public ResponseEntity<Product> getById(@PathVariable("id") Long id) {
    Product product = productRepository
      .findById(id)
      .orElseThrow(NoSuchElementException::new);

    return ResponseEntity.ok().body(product);
  }

  @PostMapping
  public ResponseEntity<Product> create(@RequestBody Product item) {
    Product createdProduct = productRepository.save(item);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
  }

  @Transactional
  @PutMapping("{id}")
  public ResponseEntity<Product> update(
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

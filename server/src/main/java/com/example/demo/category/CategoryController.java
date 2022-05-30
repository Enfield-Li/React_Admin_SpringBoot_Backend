package com.example.demo.category;

import com.example.demo.params.Sort;
import java.lang.reflect.Array;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
class CategoryController {

  @Autowired
  CategoryRepository repository;

  @GetMapping
  public ResponseEntity<Category> getAll(
    @RequestParam(name = "_end", required = false) Integer end,
    @RequestParam(name = "_start", required = false) String start,
    @RequestParam(name = "_order", required = false) String order,
    @RequestParam(name = "_sort", required = false) String sort
  ) {
    // System.out.println("category start");
    // System.out.println(_end);
    // System.out.println(_start);
    // System.out.println(_order);
    // System.out.println(_sort);
    // System.out.println("category end");

    return null;
  }

  @PutMapping("{id}")
  public ResponseEntity<Category> update(
    @PathVariable("id") Long id,
    @RequestBody Category item
  ) {
    return null;
  }

  @DeleteMapping("{id}")
  public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
    return null;
  }
}

package com.example.demo.invoices;

import com.example.demo.invoices.entity.Invoice;
import com.example.demo.invoices.repository.InvoiceRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
@Tag(name = "Invoices")
@RequestMapping("/invoices")
class InvoiceController {

  @Autowired
  InvoiceRepository repository;

  @GetMapping("test")
  public void Test() {}

  /*
   * http://localhost:3060/invoices?_end=25&_order=desc&_sort=date&_start=0
   */
  @GetMapping(params = { "_start", "_end", "_sort", "_order" })
  public ResponseEntity<List<Invoice>> getAll(
    @RequestParam(name = "_start") Integer start,
    @RequestParam(name = "_end") Integer end,
    @RequestParam(name = "_sort") String sort,
    @RequestParam(name = "_order") String order
  ) {
    List<Invoice> invoices = repository.findAll();

    return ResponseEntity
      .ok()
      .header("X-Total-Count", invoices.size() + "")
      .body(invoices);
  }

  @GetMapping("{id}")
  public ResponseEntity<Invoice> getById(@PathVariable("id") Long id) {
    return null;
  }

  @PostMapping
  public ResponseEntity<Invoice> create(@RequestBody List<Invoice> item) {
    repository.saveAll(item);
    return null;
  }

  @PutMapping("{id}")
  public ResponseEntity<Invoice> update(
    @PathVariable("id") Long id,
    @RequestBody Invoice item
  ) {
    return null;
  }

  @DeleteMapping("{id}")
  public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
    return null;
  }
}

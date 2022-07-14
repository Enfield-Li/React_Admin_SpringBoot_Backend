package com.example.demo.controller;

import com.example.demo.entity.Invoice;
import com.example.demo.mapper.InvoiceMapper;
import com.example.demo.repository.InvoiceRepository;
import io.swagger.v3.oas.annotations.tags.Tag;

import static com.example.demo.util.Constants.*;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Invoices")
@RequestMapping(INVOICES_ENDPOINT)
class InvoiceController {

  private final InvoiceMapper invoiceMapper;
  private final InvoiceRepository invoiceRepository;

  InvoiceController(
    InvoiceRepository invoiceRepository,
    InvoiceMapper invoiceMapper
  ) {
    this.invoiceRepository = invoiceRepository;
    this.invoiceMapper = invoiceMapper;
  }

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
    @RequestParam(name = "_order") String order,
    @RequestParam(name = "date_lte", required = false) String date_lte,
    @RequestParam(name = "date_gte", required = false) String date_gte
  ) {
    Integer take = end - start;

    List<Invoice> invoices = invoiceMapper.getPaginatedInvoices(
      start,
      take,
      sort,
      order,
      date_lte,
      date_gte
    );
    String invoiceCount = invoiceMapper.getInvoiceCount();

    return ResponseEntity
      .ok()
      .header("X-Total-Count", invoiceCount)
      .body(invoices);
  }
}

package com.example.demo.invoices.repository;

import com.example.demo.invoices.entity.Invoice;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface InvoiceMapper {
  @Select(
    "SELECT *" +
    " FROM invoice" +
    " ORDER BY ${sort} ${order}" +
    " LIMIT #{take} OFFSET #{start}"
  )
  public List<Invoice> getPaginatedInvoices(
    @Param("start") Integer start,
    @Param("take") Integer take,
    @Param("sort") String sort,
    @Param("order") String order
  );

  @Select("SELECT COUNT(*) FROM invoice")
  public String getInvoiceCount();
}

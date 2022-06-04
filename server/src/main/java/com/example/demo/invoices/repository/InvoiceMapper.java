package com.example.demo.invoices.repository;

import com.example.demo.invoices.entity.Invoice;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface InvoiceMapper {
  @Select("SELECT COUNT(*) FROM invoice")
  public String getInvoiceCount();

  public List<Invoice> getPaginatedInvoices(
    @Param("start") Integer start,
    @Param("take") Integer take,
    @Param("sort") String sort,
    @Param("order") String order,
    @Param("date_lte") String date_lte,
    @Param("date_gte") String date_gte
  );
}

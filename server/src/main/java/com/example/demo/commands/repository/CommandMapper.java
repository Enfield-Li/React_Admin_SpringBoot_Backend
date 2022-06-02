package com.example.demo.commands.repository;

import com.example.demo.commands.dto.CommandDto;
import com.example.demo.commands.entity.Command;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CommandMapper {
  public List<CommandDto> getPaginatedcommands(
    @Param("start") Integer start,
    @Param("take") Integer take,
    @Param("sort") String sort,
    @Param("order") String order,
    @Param("status") String status,
    @Param("date_gte") String date_gte,
    @Param("customer_id") String customer_id,
    @Param("total_gte") String total_gte
  );

  public String getCommandCount(
    @Param("status") String status,
    @Param("date_gte") String date_gte,
    @Param("customer_id") String customer_id,
    @Param("total_gte") String total_gte
  );

  public List<Command> getManyCommands(List<Long> ids);
}

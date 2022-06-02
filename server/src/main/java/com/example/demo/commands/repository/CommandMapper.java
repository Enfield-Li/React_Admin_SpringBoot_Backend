package com.example.demo.commands.repository;

import com.example.demo.commands.dto.CommandDto;
import com.example.demo.commands.entity.Command;

import java.time.Instant;
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
    @Param("date_gte") Instant date_gte,
    @Param("customer_id") String customer_id
  );

  @Select("SELECT COUNT(*) FROM command WHERE status = #{status}")
  public String getCommandCount(@Param("status") String status);

  public List<Command> getManyCommands(List<Long> ids);
}

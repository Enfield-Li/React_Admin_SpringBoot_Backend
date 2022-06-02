package com.example.demo.commands.repository;

import com.example.demo.commands.entity.Command;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CommandMapper {
  @Select(
    "SELECT *" +
    " FROM command" +
    " WHERE status = #{status}" +
    " ORDER BY ${sort} ${order}" +
    " LIMIT #{take} OFFSET #{start}"
  )
  // select c.*, b.product_id, b.quantity from command c left join basket b on b.command_id = c.id;
  public List<Command> getPaginatedcommands(
    @Param("start") Integer start,
    @Param("take") Integer take,
    @Param("sort") String sort,
    @Param("order") String order,
    @Param("status") String status
  );

  @Select("SELECT COUNT(*) FROM command WHERE status = #{status}")
  public String getCommandCount(@Param("status") String status);

  public List<Command> getManyCommands(List<Long> ids);
}

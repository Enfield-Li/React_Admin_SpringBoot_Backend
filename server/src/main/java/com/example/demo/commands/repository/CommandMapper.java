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
    " ORDER BY ${sort} ${order}" +
    " LIMIT #{take} OFFSET #{start}"
  )
  public List<Command> getPaginatedcommands(
    @Param("start") Integer start,
    @Param("take") Integer take,
    @Param("sort") String sort,
    @Param("order") String order
  );

  @Select("SELECT COUNT(*) FROM command")
  public String getCommandCount();

  public List<Command> getManyCommands(List<Long> ids);
}

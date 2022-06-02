package com.example.demo.commands.repository;

import com.example.demo.commands.dto.CommandDto;
import com.example.demo.commands.entity.Command;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CommandMapper {
  @Select(
    " SELECT c.*," +
    " group_concat( CONCAT(b.product_id,',', b.quantity ))" +
    " AS productId_quantity" +
    " FROM command c" +
    " LEFT JOIN basket b" +
    " ON b.command_id = c.id" +
    " WHERE c.status = #{status}" +
    " GROUP BY c.id" +
    " ORDER BY c.${sort} ${order}" +
    " LIMIT #{take} OFFSET #{start}"
  )
  public List<CommandDto> getPaginatedcommands(
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

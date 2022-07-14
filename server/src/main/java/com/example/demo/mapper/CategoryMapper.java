package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CategoryMapper {
  @Select("SELECT COUNT(*) FROM category")
  public String getCategoryCount();

  @Update("Update category SET name = #{name} WHERE id = #{id}")
  public Integer updateCategoryName(
    @Param("name") String name,
    @Param("id") Integer id
  );
}

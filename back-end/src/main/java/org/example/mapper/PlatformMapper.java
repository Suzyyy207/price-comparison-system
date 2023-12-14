package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.model.entity.Platform;

@Mapper
public interface PlatformMapper {
    @Select("select * from platform where id = #{id}")
    Platform findById(@Param("id")int id);
}

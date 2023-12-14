package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.model.entity.Platform;
import org.example.model.entity.Seller;

import java.util.List;

@Mapper
public interface PlatformMapper extends BaseMapper<Platform> {
    @Select("select * from platform where id = #{id}")
    Platform findById(@Param("id")int id);
    @Select("select * from platform")
    List<Platform> findAll();
}

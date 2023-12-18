package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.model.VO.PlatformVO;
import org.example.model.entity.Platform;
import org.example.model.entity.Seller;

import java.util.List;

@Mapper
public interface PlatformMapper extends BaseMapper<Platform> {
    @Select("select * from platform where id = #{id}")
    Platform findById(@Param("id")int id);
    @Select("select * from platform")
    List<Platform> findAll();
    @Select("select * from platform where name =#{name}")
    Platform findByName(@Param("name") String name);
    @Update("update platform set name=#{platformVO.name} where id=#{platformVO.id}")
    Integer update(@Param("platformVO")PlatformVO platformVO);
}

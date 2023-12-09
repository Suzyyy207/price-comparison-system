package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.model.entity.Admin;
import org.example.model.entity.User;

@Mapper
public interface AdminMapper extends BaseMapper<Admin> {
    @Select("select * from admin where id = #{id}")
    Admin findById(@Param("id")int id);

    //   查询用户，根据用户adminname查询信息   select * from admin where adminname =
    @Select("select * from admin where name = #{name}")
    Admin findByAdminName(@Param("name")String name);
}

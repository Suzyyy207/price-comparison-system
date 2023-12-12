package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.example.model.entity.User;

import java.util.List;

//用于操作用户表,MyBaits会根据Mapper注解，动态实现UserMapper接口（实现类），动态代理技术
//Spring会自动创建UserMapper接口实现类对应的实例
@Mapper
public interface UserMapper extends BaseMapper<User> {

    //   查询用户，根据用户id查询信息   select * from user where id =
    @Select("select * from user where id = #{id}")
    User findById(@Param("id")int id);

    //   查询用户，根据用户username查询信息   select * from user where username =
    @Select("select * from user where name = #{name}")
    User findByUserName(@Param("name")String name);

    //   查询用户，根据用户phone查询信息   select * from user where phone =
    @Select("select * from user where phone = #{phone}")
    User findByPhone(@Param("phone")String phone);

    @Select("select * from user ")
    List<User> findAll();
//    @Update("update t_user set username=#{updateInformationVO.username},phone=#{updateInformationVO.phone},email=#{updateInformationVO.email} where idNumber=#{updateInformationVO.idNumber}")
//    int update(@Param("updateInformationVO")UpdateInformationVO updateInformationVO);
}

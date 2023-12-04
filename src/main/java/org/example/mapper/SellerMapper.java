package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.model.entity.Seller;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SellerMapper extends BaseMapper<Seller> {

//   查询用户，根据用户id查询信息   select * from seller where id =
    @Select("select * from t_seller where id = #{id}")
    Seller findById(@Param("id") int id);

    //   查询用户，根据用户username和password查询信息
//    @Select("select * from t_seller where username = #{username} and password=#{password}")
//    Seller findByUsernameAndPassword(String username, String password);

    //   查询用户，根据用户username查询信息   select * from seller where username =
    @Select("select * from t_seller where username = #{username}")
    Seller findByUsername(@Param("username") String username);

//   查询用户及其所有的购买订单、卖出订单、所开商店


//    @Update("update seller set username=#{username},password=#{password},phone=#{phone},email=#{email} where id=#{id}")
//    int update(Seller seller);

    //插入用户(返回值是插入了几条记录，插入失败返回0)
//    @Insert("INSERT INTO t_seller (username, password, phone, email, idNumber,balance) VALUES (#{registerVO.username}, #{registerVO.password}, #{registerVO.phone}, #{registerVO.email}, #{registerVO.idNumber},0.0)")
//    int insert(@Param("registerVO") RegisterVO registerVO);
//
//    @Update("update t_seller set username=#{updateInformationVO.username},phone=#{updateInformationVO.phone},email=#{updateInformationVO.email} where idNumber=#{updateInformationVO.idNumber}")
//    int update(@Param("updateInformationVO") UpdateInformationVO updateInformationVO);
}


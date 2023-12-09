package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.model.entity.Seller;
import org.example.model.entity.User;

@Mapper
public interface SellerMapper extends BaseMapper<Seller> {
    @Select("select * from seller where id = #{id}")
    Seller findById(@Param("id")int id);

    //   查询用户，根据用户sellername查询信息   select * from seller where sellername =
    @Select("select * from seller where name = #{name}")
    Seller findBySellerName(@Param("name")String name);

    //   查询用户，根据用户address查询信息   select * from seller where address =
    @Select("select * from seller where address = #{address}")
    Seller findByAddress(@Param("address")String address);
}

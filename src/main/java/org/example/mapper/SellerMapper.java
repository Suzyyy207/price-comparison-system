package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.example.model.VO.InsertVO;
import org.example.model.VO.UpdateVO;
import org.example.model.entity.Seller;
import org.example.model.entity.User;

import java.util.List;

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
    @Select("select * from seller")
    List<Seller> findAll();
    @Insert("insert into seller (name,address) values (#{insertVO.name}, #{insertVO.address})")
    int insert(@Param("insertVO") InsertVO insertVO);
    @Update("update seller set name=#{updateVO.name},address=#{updateVO.address} where id=#{updateVO.id}")
    int update(@Param("updateVO") UpdateVO updateVO);

    @Delete("delete from seller where id=#{sellerId}")
    int deleteBySellerId(@Param("sellerId")int sellerId);
}

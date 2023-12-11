package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.model.entity.Goods;
import org.example.model.entity.User;

import java.util.List;

@Mapper
public interface GoodsMapper {
    @Select("select * from goods where id = #{id}")
    Goods findById(@Param("id")int id);

    @Select("select * from goods where tag = #{tag}")
    List<Goods> findByTag(@Param("tag")int tag);
    @Select("select * from goods where sellerId = #{sellerId} and state=1")
    List<Goods> findBySellerId(@Param("sellerId")int sellerId);
    @Select("select * from goods where state = 1")
    List<Goods> findValidGoods();
    @Select("select * from goods")
    List<Goods> findAll();
}
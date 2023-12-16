package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.example.model.RE.GetGoodsRE;
import org.example.model.VO.InsertGoodsVO;
import org.example.model.VO.InsertGoodsVO;
import org.example.model.VO.UpdateGoodsVO;
import org.example.model.entity.Goods;
import org.example.model.entity.Seller;
import org.example.model.entity.User;

import java.util.List;

@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {
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
    @Select("SELECT g.id, g.name, g.location, g.price, g.minPrice, g.category,g.productionDate, " +
            "s.name AS sellerName, p.name AS platformName, " +
            "CASE WHEN c.userId IS NOT NULL THEN 1 ELSE 0 END AS isCollect " +
            "FROM goods g " +
            "LEFT JOIN collect c ON c.goodsId=#{id} AND c.userId = #{userId} " +
            "LEFT JOIN seller s ON g.sellerId = s.id " +
            "LEFT JOIN platform p ON g.platformId = p.id " +
            "WHERE g.id = #{id}")
    GetGoodsRE findGoodsByIdAndUserId(@Param("id")int id,@Param("userId")int userId);
    @Insert("insert into goods (name,price,minPrice,location,category,sellerId,platformId,productionDate,tag,state) values (#{insertGoodsVO.name}, #{insertGoodsVO.price},#{insertGoodsVO.price}, #{insertGoodsVO.location},#{insertGoodsVO.category},#{insertGoodsVO.sellerId},#{insertGoodsVO.platformId},#{insertGoodsVO.productionDate},#{insertGoodsVO.tag},1)")
    int insert(@Param("insertGoodsVO") InsertGoodsVO insertGoodsVO);

    @Update("update goods set name=#{updateGoodsVO.name},location=#{updateGoodsVO.location},category=#{updateGoodsVO.category},sellerId=#{updateGoodsVO.sellerId},platformId=#{updateGoodsVO.platformId},productionDate=#{updateGoodsVO.productionDate},tag=#{updateGoodsVO.tag} where id=#{updateGoodsVO.id}")
    int update(@Param("updateGoodsVO") UpdateGoodsVO updateGoodsVO);
}
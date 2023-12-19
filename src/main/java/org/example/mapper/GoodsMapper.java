package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.example.model.RE.GetGoodsRE;
import org.example.model.RE.ProductRE;
import org.example.model.VO.InsertGoodsVO;
import org.example.model.VO.InsertGoodsVO;
import org.example.model.VO.SearchGoodsVO;
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
    List<Goods> findByTag(@Param("tag")String tag);
    @Select("select * from goods where sellerId = #{sellerId} and state=1")
    List<Goods> findBySellerId(@Param("sellerId")int sellerId);
    @Select("select * from goods where state = 1")
    List<Goods> findValidGoods();
    @Select("select * from goods")
    List<Goods> findAll();
    @Select("select * from goods where tag=#{tag} "+
            "and sellerId = (select id from seller where name = #{sellerName}) " +
            "and platformId = (select id from platform where name = #{platformName})")
    Goods findByTagPlatformSeller(@Param("sellerName")String sellerName,@Param("tag")String tag,@Param("platformName")String platformName);
    @Select("select g.id, g.name, g.location, g.price, g.minPrice, g.category,g.productionDate, " +
            "s.name AS sellerName, p.name AS platformName, " +
            "CASE WHEN c.userId IS NOT NULL THEN 1 ELSE 0 END AS isCollect " +
            "from goods g " +
            "left join collect c ON c.goodsId=#{id} and c.userId = #{userId} " +
            "left join seller s ON g.sellerId = s.id " +
            "left join platform p ON g.platformId = p.id " +
            "where g.id = #{id}")
    GetGoodsRE findGoodsByIdAndUserId(@Param("id")int id,@Param("userId")int userId);

    @Select("SELECT g.id, g.name, g.location, g.price, g.minPrice, g.category, " +
            "s.name AS sellerName, p.name AS platformName, g.productionDate,g.tag " +
            "FROM goods g " +
            "LEFT JOIN seller s ON g.sellerId = s.id " +
            "LEFT JOIN platform p ON g.platformId = p.id " +
            "WHERE " +
            "#{searchGoodsVO.type} = 1 AND g.name LIKE CONCAT('%', #{searchGoodsVO.keyword}, '%') " +
            "OR " +
            "#{searchGoodsVO.type} = 2 AND g.tag LIKE CONCAT('%', #{searchGoodsVO.keyword}, '%') " +
            "OR " +
            "#{searchGoodsVO.type} = 3 AND g.category LIKE CONCAT('%', #{searchGoodsVO.keyword}, '%')")
    List<ProductRE> findGoodsByTypeAndKeyword(@Param("searchGoodsVO") SearchGoodsVO searchGoodsVO);


    @Insert("insert into goods (name,price,minPrice,location,category,sellerId,platformId,productionDate,tag,state) values (#{insertGoodsVO.name}, #{insertGoodsVO.price},#{insertGoodsVO.price}, #{insertGoodsVO.location},#{insertGoodsVO.category},"+
            "(select id from seller where name = #{insertGoodsVO.sellerName}), " +
            "(select id from platform where name = #{insertGoodsVO.platformName}), " +
            "#{insertGoodsVO.productionDate},#{insertGoodsVO.tag},1)")
    int insert(@Param("insertGoodsVO") InsertGoodsVO insertGoodsVO);

    @Update("update goods set name=#{updateGoodsVO.name},location=#{updateGoodsVO.location},price=#{updateGoodsVO.price},category=#{updateGoodsVO.category},"+
            "sellerId=(SELECT id FROM seller WHERE name = #{updateGoodsVO.sellerName})," +
            "platformId=(SELECT id FROM platform WHERE name = #{updateGoodsVO.platformName}),"+
            "minPrice=CASE WHEN #{updateGoodsVO.price} < minPrice THEN #{updateGoodsVO.price} ELSE minPrice END,"+
            "productionDate=#{updateGoodsVO.productionDate},tag=#{updateGoodsVO.tag} where id=#{updateGoodsVO.goodsId}")
    int update(@Param("updateGoodsVO") UpdateGoodsVO updateGoodsVO);
    @Update("update goods set minPrice = #{updateGoodsVO.price}where id=#{updateGoodsVO.goodsId}")
    int updateMinPrice(@Param("updateGoodsVO") UpdateGoodsVO updateGoodsVO);

    @Delete("delete from goods where id = #{goodsId}")
    Integer deleteByGoodsId(@Param("goodsId")int goodsId);
}
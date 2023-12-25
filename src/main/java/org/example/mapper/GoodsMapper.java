package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.*;
import org.example.model.RE.*;
import org.example.model.VO.*;
import org.example.model.VO.InsertGoodsVO;
import org.example.model.entity.Goods;
import org.example.model.entity.Seller;
import org.example.model.entity.User;

import java.util.List;
import java.util.Map;

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
    @Select("select g.id, g.name, g.location, g.price, g.minPrice, g.category,g.productionDate, g.tag, " +
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
    @Select("EXPLAIN SELECT g.id, g.name, g.location, g.price, g.minPrice, g.category, " +
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
    List<Map<String, Object>> explainFindGoods(@Param("searchGoodsVO") SearchGoodsVO searchGoodsVO);

    @Select("WITH GoodsCollected AS (" +
            "SELECT g.id AS goodsId, g.tag, g.name, c.userId " +
            "FROM goods g LEFT JOIN collect c ON g.id = c.goodsId " +
            "WHERE YEAR(c.collect_date) = YEAR(CURRENT_DATE()) and c.state=1), " +
            "CollectCounts AS (" +
            "SELECT gc.tag, gc.goodsId, gc.name, COUNT(gc.userId) AS collectCount " +
            "FROM GoodsCollected gc GROUP BY gc.tag, gc.goodsId), " +
            "MaxCollectPerTag AS (" +
            "SELECT tag, goodsId, name, collectCount, RANK() OVER (PARTITION BY tag ORDER BY collectCount DESC) AS rnk " +
            "FROM CollectCounts) "+
            "SELECT mc.tag, mc.goodsId, mc.name, mc.collectCount " +
            "FROM MaxCollectPerTag mc WHERE mc.rnk = 1 and mc.collectCount > 0;")
    List<TopTagCollectRE> getTop4Year();

    @Select("WITH GoodsCollect AS (" +
            "SELECT g.id AS goodsId, g.tag, c.userId " +
            "FROM goods g INNER JOIN collect c ON g.id = c.goodsId " +
            "WHERE c.state = 1 and YEAR(c.collect_date) = YEAR(CURRENT_DATE())), " +
            "CollectWithSex AS (" +
            "SELECT COUNT(gc.goodsId) AS goodsCCount, gc.tag, gc.userId, u.sex " +
            "FROM GoodsCollect gc LEFT JOIN user u ON gc.userId = u.id GROUP BY gc.tag, gc.userId), "+
            "SexTagCollect AS (" +
            "SELECT SUM(cws.goodsCCount) as count, cws.tag, cws.sex "+
            "FROM CollectWithSex cws GROUP BY cws.tag, cws.sex), "+
            "MaxSexTag AS(" +
            "SELECT sex, tag, count, RANK() OVER (PARTITION BY sex ORDER BY count DESC) AS rnk "+
            "From SexTagCollect) " +
            "SELECT sex,tag,count,rnk " +
            "From MaxSexTag where rnk <=3;")
    List<TopTag4SexRE> getTop4Sex();

    @Select("SELECT DISTINCT(tag) FROM goods WHERE tag LIKE concat('%',#{keyword},'%') and state=1;")
    List<String> getTags(@Param("keyword") String keyword);

    @Select("SELECT g.id, g.tag, g.name AS goodsName, p.name AS platformName, s.name AS sellerName, g.price, g.minPrice " +
            "FROM (goods g LEFT JOIN platform p ON g.platformId = p.id) LEFT JOIN seller s on s.id = g.sellerId " +
            "WHERE tag LIKE concat('%',#{keyword},'%');")
    List<PriceCompareRE> getTagPriceAll(@Param("keyword") String keyword);

    @Select("SELECT g.id, g.tag, g.name AS goodsName, p.name AS platformName, s.name AS sellerName, g.price, g.minPrice " +
            "FROM (goods g LEFT JOIN platform p ON g.platformId = p.id) LEFT JOIN seller s on s.id = g.sellerId " +
            "WHERE tag LIKE concat('%',#{keyword},'%') and p.id = #{platformId};")
    List<PriceCompareRE> getTagPrice(@Param("keyword") String keyword, @Param("platformId") Integer platformId);

    @Select("WITH GoodsInfo AS(" +
            "SELECT g.id, g.tag, g.name AS goodsName, p.name AS platformName, s.name AS sellerName " +
            "FROM (goods g LEFT JOIN platform p ON g.platformId = p.id) LEFT JOIN seller s on s.id = g.sellerId " +
            "WHERE tag LIKE concat('%',#{priceDiff.tag},'%')), " +
            "GoodsPrice AS(" +
            "SELECT h.goodsId, h.price " +
            "FROM History h " +
            "WHERE h.goodsId in (SELECT id FROM GoodsInfo) and DATEDIFF(NOW(), h.pDate) < #{priceDiff.time_len}), " +
            "PriceDiff AS(" +
            "SELECT gp.goodsId, MAX(gp.price) AS maxPrice, MIN(gp.price) AS minPrice, MAX(gp.price)-MIN(gp.price) AS priceDiffNum " +
            "FROM GoodsPrice gp GROUP BY gp.goodsID) " +
            "SELECT gi.id AS id, gi.tag, gi.goodsName, gi.platformName, gi.sellerName, pd.maxPrice, pd.minPrice, pd.priceDiffNum " +
            "FROM GoodsInfo AS gi LEFT JOIN PriceDiff pd ON gi.id = pd.goodsId;"
    )
    List<PriceDiffRE> getPriceDiffAll(@Param("priceDiff")PriceDiffVO priceDiff);

    @Select("WITH GoodsInfo AS(" +
            "SELECT g.id, g.tag, g.name AS goodsName, p.name AS platformName, s.name AS sellerName " +
            "FROM (goods g LEFT JOIN platform p ON g.platformId = p.id) LEFT JOIN seller s on s.id = g.sellerId " +
            "WHERE tag LIKE concat('%',#{priceDiff.tag},'%') AND g.platformId = #{priceDiff.platform_id}), " +
            "GoodsPrice AS(" +
            "SELECT h.goodsId, h.price " +
            "FROM History h " +
            "WHERE h.goodsId in (SELECT id FROM GoodsInfo) and DATEDIFF(NOW(), h.pDate) < #{priceDiff.time_len}), " +
            "PriceDiff AS(" +
            "SELECT gp.goodsId, MAX(gp.price) AS maxPrice, MIN(gp.price) AS minPrice, MAX(gp.price)-MIN(gp.price) AS priceDiffNum " +
            "FROM GoodsPrice gp GROUP BY gp.goodsID) " +
            "SELECT gi.id AS id, gi.tag, gi.goodsName, gi.platformName, gi.sellerName, pd.maxPrice, pd.minPrice, pd.priceDiffNum " +
            "FROM GoodsInfo AS gi LEFT JOIN PriceDiff pd ON gi.id = pd.goodsId;"
    )
    List<PriceDiffRE> getPriceDiff(@Param("priceDiff")PriceDiffVO priceDiff);

    @Insert("insert into goods (name,price,minPrice,location,category,sellerId,platformId,productionDate,tag,state) values (#{insertGoodsVO.name}, #{insertGoodsVO.price},#{insertGoodsVO.price}, #{insertGoodsVO.location},#{insertGoodsVO.category},"+
            "(select id from seller where name = #{insertGoodsVO.sellerName}), " +
            "(select id from platform where name = #{insertGoodsVO.platformName}), " +
            "#{insertGoodsVO.productionDate},#{insertGoodsVO.tag},1)")
    int insert(@Param("insertGoodsVO") InsertGoodsVO insertGoodsVO);

    @Insert("insert into goods (name,price,minPrice,location,category,sellerId,platformId,productionDate,tag,state) values"+
            " (#{goods.name}, #{goods.price},#{goods.minPrice}, #{goods.location},#{goods.category},"+
            " #{goods.sellerId},  #{goods.platformId}, #{goods.productionDate},#{goods.tag},#{goods.state})")
    int insertFile(@Param("goods")Goods goods);
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
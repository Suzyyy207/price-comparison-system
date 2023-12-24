package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import javafx.util.Pair;
import org.apache.ibatis.annotations.*;
import org.example.model.RE.CollectTagRE;
import org.example.model.RE.ProbabilityRE;
import org.example.model.RE.ProductRE;
import org.example.model.VO.CollectVO;
import org.example.model.entity.Collect;
import org.example.model.entity.Goods;
import org.example.model.entity.Seller;

import java.util.List;

@Mapper
public interface CollectMapper extends BaseMapper<Collect> {
    @Select("select * from collect where id = #{id}")
    Collect findById(@Param("id")int id);

    @Select("select g.id, g.name, g.location, g.price, g.minPrice, g.category, s.name as sellerName, p.name as platformName, g.productionDate, g.tag " +
            "from collect c " +
            "JOIN goods g ON c.goodsId = g.id " +
            "JOIN seller s ON g.sellerId = s.id " +
            "JOIN platform p ON g.platformId = p.id " +
            "where c.userId = #{userId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "location", column = "location"),
            @Result(property = "price", column = "price"),
            @Result(property = "minPrice", column = "minPrice"),
            @Result(property = "category", column = "category"),
            @Result(property = "sellerName", column = "sellerName"),
            @Result(property = "platformName", column = "platformName"),
            @Result(property = "productionDate", column = "productionDate"),
            @Result(property = "tag", column = "tag")
    })
    List<ProductRE> findByUserId(@Param("userId")int userId);

    @Select("select AVG(expectPrice) as AvgExpectPrice " +
            "from collect " +
            "where goodsId =#{goodsId}  and expectPrice is not null;")
    Double findAverageExpectPriceByGoodsId(Integer goodsId);
    
    @Select("select distinct g.tag as tag, COUNT(*) as count from collect "+
            "JOIN goods g ON collect.goodsId = g.id "+
            "where userId = #{userId} group by g.tag;")
    List<CollectTagRE> findCollectTagByUserId(@Param("userId")Integer userId);

    @Select("SELECT c.goodsId, g.name, g.category, s.name as sellerName, p.name as platformName, COUNT(m.id) / COUNT(h.price) as probability " +
            "FROM collect c " +
            "LEFT JOIN history h ON c.goodsId = h.goodsId " +
            "LEFT JOIN message m ON c.goodsId = m.goodsId "+
            "LEFT JOIN goods g ON c.goodsId = g.id " +
            "LEFT JOIN seller s ON g.sellerId = s.id " +
            "LEFT JOIN platform p ON g.platformId = p.id " +
            "WHERE c.userId = #{userId} " +
            "GROUP BY c.goodsId;")
    List<ProbabilityRE> findCollectProbability(@Param("userId")Integer userId);
    @Insert("insert into collect (userId,goodsId,expectPrice,state,collect_date) values (#{collectVO.userId},#{collectVO.goodsId},#{collectVO.expectPrice},1,NOW())")
    int insert(@Param("collectVO") CollectVO collectVO);
    @Update("update collect set expectPrice=#{collectVO.expectPrice} where goodsId=#{collectVO.goodsId} and userId=#{collectVO.userId}")
    Integer update(@Param("collectVO") CollectVO collectVO);
    @Update("update collect set state=0, goodsId=0, name=#{name}  where goodsId=#{goodsId}")
    Integer setUnvalid(@Param("goodsId")int goodsId, @Param("name")String name);

    @Delete("delete from collect where userId = #{userId}")
    Integer deleteByUserId(@Param("userId")int userId);

    @Delete("delete from collect where userId=#{collectVO.userId} and goodsId=#{collectVO.goodsId}")
    Integer deleteCollect(@Param("collectVO")CollectVO collectVO);

}

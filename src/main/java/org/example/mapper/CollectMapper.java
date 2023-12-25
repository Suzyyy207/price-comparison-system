package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import javafx.util.Pair;
import org.apache.ibatis.annotations.*;
import org.example.model.RE.AnalysisRE;
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

    @Select("select g.id, g.name, g.location, g.price, g.minPrice, g.category, s.name as sellerName, p.name as platformName," +
            " g.productionDate, g.tag, c.expectPrice " +
            "from collect c " +
            "JOIN goods g ON c.goodsId = g.id " +
            "JOIN seller s ON g.sellerId = s.id " +
            "JOIN platform p ON g.platformId = p.id " +
            "where c.userId = #{userId} and c.state=1")
    List<ProductRE> findByUserId(@Param("userId")int userId);

    @Select("select AVG(c.expectPrice) as avgPrice, COUNT(c.expectPrice) as count, g.name as goodsName " +
            "from collect c left join goods g on c.goodsId = g.id " +
            "where g.sellerId =#{sellerId} and c.expectPrice is not null " +
            "group by c.goodsId;")
    List<AnalysisRE> findAverageExpectPriceByGoodsId(@Param("sellerId")Integer sellerId);
    
    @Select("select distinct g.tag as tag, COUNT(*) as count from collect "+
            "JOIN goods g ON collect.goodsId = g.id "+
            "where collect.state=1 and userId = #{userId} group by g.tag " +
            "order by count desc;")
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
    @Select("select COUNT(distinct m.goodsId) * 1.0 / COUNT(distinct c.goodsId) AS collectProbability "+
            "FROM collect c " +
            "left join message m ON m.goodsId = c.goodsId " +
            "WHERE c.userId = #{userId};")
    Double findAllCollectProbability(@Param("userId")Integer userId);
    @Insert("insert into collect (userId,goodsId,expectPrice,state,collect_date,name) " +
            "values (#{collectVO.userId},#{collectVO.goodsId},#{collectVO.expectPrice},1,NOW(),(select name from goods where id=#{collectVO.goodsId}))")
    int insert(@Param("collectVO") CollectVO collectVO);
    @Update("update collect set expectPrice=#{collectVO.expectPrice} where goodsId=#{collectVO.goodsId} and userId=#{collectVO.userId}")
    Integer update(@Param("collectVO") CollectVO collectVO);
    //TODO: 这里最后要改为引用1
    @Update("update collect set state=0, goodsId=1, name=#{name}  where goodsId=#{goodsId}")
    Integer setUnvalid(@Param("goodsId")int goodsId, @Param("name")String name);

    @Delete("delete from collect where userId = #{userId}")
    Integer deleteByUserId(@Param("userId")int userId);

    @Delete("delete from collect where userId=#{collectVO.userId} and goodsId=#{collectVO.goodsId}")
    Integer deleteCollect(@Param("collectVO")CollectVO collectVO);

}

package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
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

    @Select("SELECT g.id, g.name, g.location, g.price, g.minPrice, g.category, s.name AS sellerName, p.name AS platformName, g.productionDate " +
            "FROM collect c " +
            "JOIN goods g ON c.goodsId = g.id " +
            "JOIN seller s ON g.sellerId = s.id " +
            "JOIN platform p ON g.platformId = p.id " +
            "WHERE c.userId = #{userId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "location", column = "location"),
            @Result(property = "price", column = "price"),
            @Result(property = "minPrice", column = "minPrice"),
            @Result(property = "category", column = "category"),
            @Result(property = "sellerName", column = "sellerName"),
            @Result(property = "platformName", column = "platformName"),
            @Result(property = "productionDate", column = "productionDate")
    })
    List<ProductRE> findByUserId(@Param("userId")int userId);

    @Insert("insert into collect (userId,goodsId,expectPrice,state) values (#{collectVO.userId},#{collectVO.goodsId},#{collectVO.expectPrice},1)")
    Integer insert(CollectVO collectVO);
    @Update("")
    Integer update();
}
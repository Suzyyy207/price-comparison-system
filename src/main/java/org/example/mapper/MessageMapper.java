package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.example.model.entity.Goods;
import org.example.model.entity.Message;

import java.util.List;

@Mapper
public interface MessageMapper extends BaseMapper<Message> {
    @Select("select * from message where id = #{id}")
    Message findById(@Param("id")int id);
    @Select("select * from message where userId = #{userId}")
    List<Message> findByUserId(@Param("userId")int userId);
    @Select("select * from message where goodsId=#{goodsId}")
    List<Message> findByGoodsId(@Param("goodsId")int goodsId);
    @Update("update message set isRead=0 where id = #{id}")
    Integer updateIsRead(@Param("id") int id);
    @Insert("INSERT INTO message (goodsId, userId, name, currentPrice, targetPrice, date, isRead) " +
            "SELECT c.goodsId, c.userId, g.name, #{oneGoods.price} AS currentPrice, c.expectPrice AS targetPrice, NOW(), 0 " +
            "FROM collect c " +
            "JOIN goods g ON c.goodsId = g.id " +
            "WHERE g.price <= c.expectPrice and g.id=#{oneGoods.id}")
    Integer insert(@Param("oneGoods")Goods oneGoods);
    @Insert("insert into message (goodsId,userId,name,type) values(1,#{userId},#{name},0)")
    Integer insertDeleteMessage(@Param("name")String name, @Param("userId")int userId);
    @Delete("delete from message where userId = #{userId}")
    Integer deleteByUserId(@Param("userId")int userId);
    @Delete("delete from message where goodsId=#{goodsId}")
    Integer deleteByGoodsId(@Param("goodsId")int goodsId);


}

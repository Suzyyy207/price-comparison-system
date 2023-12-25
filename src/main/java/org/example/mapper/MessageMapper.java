package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.example.model.VO.CollectVO;
import org.example.model.VO.SearchMessageVO;
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
    @Select("select distinct(userId) from message where goodsId=#{goodsId}")
    List<Integer> findByGoodsId2(@Param("goodsId")int goodsId);

    @Select("SELECT * FROM message WHERE userId = #{searchMessageVO.userId} AND name LIKE CONCAT('%', #{searchMessageVO.keyword}, '%')")
    List<Message> findMessagesBySearchMessageVO(@Param("searchMessageVO")SearchMessageVO searchMessageVO);

    @Update("update message set isRead=0 where id = #{id}")
    Integer updateIsRead(@Param("id") int id);
    @Insert("INSERT INTO message (goodsId, userId, name, currentPrice, targetPrice, date, isRead) " +
            "SELECT c.goodsId, c.userId, g.name, #{oneGoods.price} AS currentPrice, c.expectPrice AS targetPrice, NOW(), 0 " +
            "FROM collect c " +
            "JOIN goods g ON c.goodsId = g.id " +
            "WHERE g.price <= c.expectPrice and g.id=#{oneGoods.id}")
    Integer insert(@Param("oneGoods")Goods oneGoods);
    @Insert("INSERT INTO message (goodsId, userId, name, currentPrice, targetPrice, date, isRead) " +
            "VALUES (#{goods.id}, #{collectVO.userId}, #{goods.name}, #{goods.price}, #{collectVO.expectPrice}, NOW(), 0)")
    Integer insertGoods(@Param("goods")Goods goods,@Param("collectVO")CollectVO collectVO);
    //TODO: 这里最后要改为引用1
    @Insert("insert into message (goodsId,userId,name,type) values(6,#{userId},#{name},0)")
    Integer insertDeleteMessage(@Param("name")String name, @Param("userId")int userId);
    @Delete("delete from message where userId = #{userId}")
    Integer deleteByUserId(@Param("userId")int userId);
    @Delete("delete from message where goodsId=#{goodsId}")
    Integer deleteByGoodsId(@Param("goodsId")int goodsId);


}

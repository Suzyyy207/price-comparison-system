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
    @Update("update message set isRead=0 where id = #{id}")
    Integer updateIsRead(@Param("id") int id);
    @Insert("INSERT INTO message (goodsId, userId, name, currentPrice, targetPrice, date, isRead) " +
            "SELECT c.goodsId, c.userId, g.name, g.price AS currentPrice, c.expectPrice AS targetPrice, NOW(), 0 " +
            "FROM collect c " +
            "JOIN goods g ON c.goodsId = g.id " +
            "WHERE g.price < c.expectPrice")
    Integer insert();
}

package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.example.model.VO.InsertGoodsVO;
import org.example.model.entity.Goods;
import org.example.model.entity.History;

import java.sql.Date;
import java.util.List;


@Mapper
public interface HistoryMapper extends BaseMapper<History> {

    @Select("select * from history where goodsId = #{goodsId}")
    List<History> findByGoodsId(@Param("goodsId")int goodsId);
    @Select("select * from history where goodsId=#{goodsId} and pDate=#{pDate} and userType=#{userType}")
    List<History> findByGoodsIdAndDate(@Param("goodsId")int goodsId,@Param("pDate")Date pDate,@Param("userType")int userType);

    @Select("select * from history where goodsId = #{goodsId} and DATEDIFF(NOW(), pDate) < #{timeLen};")
    List<History> findByGoodsIdandTime(@Param("goodsId") int goodsId, @Param("timeLen")int timeLen);

    @Insert("insert into history (goodsId,price,pDate,userType) values (#{goods.id},#{goods.price},CURDATE(),#{userType})")
    Integer insertHistory(@Param("goods")Goods goods,@Param("userType")int userType);
}
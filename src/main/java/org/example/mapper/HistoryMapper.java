package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.example.model.VO.InsertGoodsVO;
import org.example.model.entity.Goods;
import org.example.model.entity.History;

import java.util.Date;
import java.util.List;


@Mapper
public interface HistoryMapper extends BaseMapper<History> {

    @Select("select * from history where goodsId = #{goodsId}")
    List<History> findByGoodsId(@Param("goodsId")int goodsId);

    @Insert("insert into history (goodsId,price,pDate) values (#{goods.id},#{goods.price},NOW())")
    Integer insertHistory(@Param("goods")Goods goods);
}
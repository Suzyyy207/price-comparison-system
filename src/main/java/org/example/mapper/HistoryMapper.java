package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.example.model.entity.History;

import java.util.List;

@Mapper
public interface HistoryMapper extends BaseMapper<History> {

    @Select("select * from history where goodsId = #{goodsId}")
    List<History> findByGoodsId(@Param("goodsId")int goodsId);

}
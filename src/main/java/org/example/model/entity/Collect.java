package org.example.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("collect")
public class Collect {
    @TableId(type = IdType.AUTO)
    protected Integer id;
    protected Integer userId;
    protected Integer goodsId;
    protected double expectPrice;
    protected Integer state;//1表示收藏成功，0表示商品被删除的时候需要设置失效

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public double getExpectPrice() {
        return expectPrice;
    }

    public void setExpectPrice(double expectPrice) {
        this.expectPrice = expectPrice;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }


}

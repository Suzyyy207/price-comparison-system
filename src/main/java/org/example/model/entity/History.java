package org.example.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.sql.Date;

@TableName("history")
public class History {
    @TableId(type = IdType.AUTO)
    protected Integer id;

    protected Integer goodsId;//商品名

    public History(Integer goodsId, double price, Integer userType, Date pDate) {
        this.goodsId = goodsId;
        this.price = price;
        this.userType = userType;
        this.pDate = pDate;
    }

    protected double price; //设定价格

    protected Integer userType;//用户类型  商家1  管理员0
    protected Date pDate;  //设定日期

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getpDate() {
        return pDate;
    }

    public void setpDate(Date pDate) {
        this.pDate = pDate;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

}

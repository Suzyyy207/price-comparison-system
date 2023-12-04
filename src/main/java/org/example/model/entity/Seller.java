package org.example.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("t_seller")
public class Seller extends UserBase{
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    protected String address;

}

package org.example.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("seller")
public class Seller {
    protected String address;
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    @TableId(type = IdType.AUTO)
    protected int id;
    protected String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void update(Seller newSeller) {
        setId(newSeller.getId());
        setName(newSeller.getName());
        setAddress(newSeller.getAddress());

    }
    public Seller(){}
    public Seller(Seller seller){
        this.id=seller.getId();
        this.name=seller.getName();
        this.address=seller.getAddress();
    }
}

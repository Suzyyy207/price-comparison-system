package org.example.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;


public class UserBase {
    public UserBase() {
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


    public UserBase(User user) {
        this.id = user.getId();
        this.name= user.getName();
    }
    public UserBase(Seller seller){
        this.id=seller.getId();
        this.name=seller.getName();
    }
    public UserBase(Admin admin){
        this.id=admin.getId();
        this.name=admin.getName();
    }
}

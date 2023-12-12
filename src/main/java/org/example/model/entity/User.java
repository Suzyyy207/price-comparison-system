package org.example.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.example.model.VO.InsertVO;

import java.util.ArrayList;
@TableName("user")
public class User {
    protected int age;
    protected int sex;
    protected String phone;
    @TableId(type = IdType.AUTO)
    protected int id;
    protected String name;
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



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
    public User(User user) {
        this.id= user.getId();
        this.name= user.getName();
        this.sex=user.getSex();
        this.age=user.getAge();
        this.phone= user.getPhone();
    }
    public void update(User newUser){
        setId(newUser.getId());
        setName(newUser.getName());
        setAge(newUser.getAge());
        setSex(newUser.getSex());
        setPhone(newUser.getPhone());
    }
    public User(){}
}

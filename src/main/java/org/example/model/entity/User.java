package org.example.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("t_user")
public class User extends UserBase{

    protected int age;
    protected int sex;
    protected String phone;
    public User(){}

    public User(User user) {
        super(user);
        this.phone = "";
        this.age = 0;
        this.sex = 0;
    }
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
}
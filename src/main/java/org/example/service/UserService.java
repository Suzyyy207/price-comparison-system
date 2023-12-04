package org.example.service;

import org.example.mapper.UserMapper;
import org.example.model.entity.UserBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserService")
public class UserService {
    private final UserMapper userMapper;
    @Autowired
    public UserService(UserMapper userMapper){
        this.userMapper=userMapper;
    }
    public UserBase getUserById(int id) {
        return userMapper.findById(id);
    }
}

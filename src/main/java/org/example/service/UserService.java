package org.example.service;

import org.example.mapper.UserMapper;
import org.example.model.VO.InsertVO;
import org.example.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserService")
public class UserService {
    private final UserMapper userMapper;
    @Autowired
    public UserService(UserMapper userMapper){
        this.userMapper=userMapper;
    }
    public User getUserById(int id) {
        return userMapper.findById(id);
    }
    public Boolean insertUser(InsertVO insertVO){
        return userMapper.insert(insertVO)==1;
    }
}

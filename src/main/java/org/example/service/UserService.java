package org.example.service;

import org.example.mapper.CollectMapper;
import org.example.mapper.MessageMapper;
import org.example.mapper.UserMapper;
import org.example.model.RE.ProductRE;
import org.example.model.VO.InsertVO;
import org.example.model.VO.UpdateVO;
import org.example.model.entity.Message;
import org.example.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserService")
public class UserService {
    private final UserMapper userMapper;
    private final CollectMapper collectMapper;
    private final MessageMapper messageMapper;
    @Autowired
    public UserService(UserMapper userMapper,CollectMapper collectMapper,MessageMapper messageMapper){
        this.userMapper=userMapper;
        this.collectMapper=collectMapper;
        this.messageMapper=messageMapper;
    }
    public User getUserById(int id) {
        return userMapper.findById(id);
    }
    public Boolean insertUser(InsertVO insertVO){
        return userMapper.insert(insertVO)==1;
    }
    public Boolean updateUser(UpdateVO updateVO){
        return userMapper.update(updateVO)==1;
    }
    public List<ProductRE> getGoodsByUserId(Integer userId){
        return collectMapper.findByUserId(userId);
    }
    public List<Message> getAllMessages(Integer userId){
        return messageMapper.findByUserId(userId);
    }
}

package org.example.service;

import org.example.mapper.CollectMapper;
import org.example.mapper.GoodsMapper;
import org.example.mapper.MessageMapper;
import org.example.mapper.UserMapper;
import org.example.model.RE.ProductRE;
import org.example.model.VO.*;
import org.example.model.entity.Message;
import org.example.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("UserService")
public class UserService {
    private final UserMapper userMapper;
    private final CollectMapper collectMapper;
    private final MessageMapper messageMapper;
    private final GoodsMapper goodsMapper;
    @Autowired
    public UserService(UserMapper userMapper, CollectMapper collectMapper, MessageMapper messageMapper, GoodsMapper goodsMapper){
        this.userMapper=userMapper;
        this.collectMapper=collectMapper;
        this.messageMapper=messageMapper;
        this.goodsMapper = goodsMapper;
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

    @Transactional
    public Boolean setCollect(CollectVO collectVO){
        return collectMapper.insert(collectVO)==1;
    }

    @Transactional
    public Boolean cancelCollect(CollectVO collectVO){
        return collectMapper.deleteCollect(collectVO)==1;
    }

    @Transactional
    public Boolean updateCollectPrice(CollectVO collectVO){
        boolean succeed = collectMapper.update(collectVO)==1;
        //检查设定价格之后商品是否符合新的目标价格，有的话在message中插入
        messageMapper.insert(goodsMapper.findById(collectVO.getGoodsId()));
        return succeed;
    }

    @Transactional
    public Boolean deleteUserById(Integer userId){
        //删除消息列表相关，删除收藏夹相关信息，删除用户
        boolean succeedMessage = messageMapper.deleteByUserId(userId)==1;
        boolean succeedCollect = collectMapper.deleteByUserId(userId)==1;
        boolean succeedUser = userMapper.deleteByUserId(userId)==1;
        if ((succeedMessage && succeedCollect) && succeedUser){
            return true;
        }
        else{
            return false;
        }
    }
    @Transactional
    public List<ProductRE> searchGoods(SearchGoodsVO searchGoodsVO){
        try {
            List<ProductRE> goods = goodsMapper.findGoodsByTypeAndKeyword(searchGoodsVO);
            return goods;
        }
        catch (Exception e) {
            // 处理异常情况，比如打印异常信息或者进行其他处理
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    public List<Message> searchMessage(SearchMessageVO searchMessageVO){
        try {
            List<Message> messages=messageMapper.findMessagesBySearchMessageVO(searchMessageVO);
            return messages;
        }
        catch (Exception e) {
            // 处理异常情况，比如打印异常信息或者进行其他处理
            e.printStackTrace();
            return null;
        }
    }
}

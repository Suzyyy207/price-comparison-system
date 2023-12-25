package org.example.service;

import org.example.mapper.CollectMapper;
import org.example.mapper.GoodsMapper;
import org.example.mapper.MessageMapper;
import org.example.mapper.UserMapper;
import org.example.model.RE.CollectTagRE;
import org.example.model.RE.ProbabilityRE;
import org.example.model.RE.ProductRE;
import org.example.model.VO.*;
import org.example.model.entity.Goods;
import org.example.model.entity.Message;
import org.example.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
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
    @Transactional
    public Boolean updateUser(UpdateVO updateVO){
        Integer id=updateVO.getId();
        User user = getUserById(id);
        if(updateVO.getAge()==0){updateVO.setAge(user.getAge());}
        if(updateVO.getName()==""){updateVO.setName(user.getName());}
        if(updateVO.getPhone()==""){updateVO.setPhone(user.getPhone());}
        return userMapper.update(updateVO)==1;
    }
    public List<ProductRE> getGoodsByUserId(Integer userId){
        return collectMapper.findByUserId(userId);
    }
    @Transactional
    public List<Message> getAllMessages(Integer userId){
        List<Message> messages=messageMapper.findByUserId(userId);
        Collections.sort(messages, new Comparator<Message>() {
            @Override
            public int compare(Message message1, Message message2) {
                // 降序排序
                return Integer.compare(message2.getId(), message1.getId());
            }
        });
        messageMapper.updateIsRead(userId);
        return messages;
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
        Goods good = goodsMapper.findById(collectVO.getGoodsId());
        if(good.getPrice() < collectVO.getExpectPrice()){
            messageMapper.insertGoods(good,collectVO);
        }

        return succeed;
    }

    @Transactional
    public Boolean deleteUserById(Integer userId){
        //删除消息列表相关，删除收藏夹相关信息，删除用户
        boolean succeedMessage = messageMapper.deleteByUserId(userId)==1;
        boolean succeedCollect = collectMapper.deleteByUserId(userId)==1;
        boolean succeedUser = userMapper.deleteByUserId(userId)==1;
        if (succeedUser){
            return true;
        }
        else{
            return false;
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
    @Transactional
    public List<CollectTagRE> getCollectTag(Integer userId){
        try {
            List<CollectTagRE> collectTagREs = collectMapper.findCollectTagByUserId(userId);
            return collectTagREs;
        }
        catch (Exception e) {
            // 处理异常情况，比如打印异常信息或者进行其他处理
            e.printStackTrace();
            return null;
        }
    }
    @Transactional
    public List<ProbabilityRE> getCollectProbability(Integer userId){
        try {
            List<ProbabilityRE> probabilityREs = collectMapper.findCollectProbability(userId);
            return probabilityREs;
        }
        catch (Exception e) {
            // 处理异常情况，比如打印异常信息或者进行其他处理
            e.printStackTrace();
            return null;
        }
    }
    @Transactional
    public Double getAllCollectProbability(Integer userId){
        try {
            Double allCollectProbability = collectMapper.findAllCollectProbability(userId);
            return allCollectProbability;
        }
        catch (Exception e) {
            // 处理异常情况，比如打印异常信息或者进行其他处理
            e.printStackTrace();
            return null;
        }
    }
}

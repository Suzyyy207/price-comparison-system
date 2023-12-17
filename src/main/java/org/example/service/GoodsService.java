package org.example.service;

import org.example.mapper.*;
import org.example.model.RE.GetGoodsRE;
import org.example.model.RE.ProductRE;
import org.example.model.VO.InsertGoodsVO;
import org.example.model.VO.UpdateGoodsVO;
import org.example.model.entity.Goods;
import org.example.model.entity.History;
import org.example.model.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

@Service("GoodsService")
public class GoodsService {
    private final GoodsMapper goodsMapper;
    private final SellerMapper sellerMapper;
    private final PlatformMapper platformMapper;

    private final HistoryMapper historyMapper;
    private final MessageMapper messageMapper;
    private final CollectMapper collectMapper;
    @Autowired
    public GoodsService(GoodsMapper goodsMapper, SellerMapper sellerMapper, PlatformMapper platformMapper, HistoryMapper historyMapper, MessageMapper messageMapper, CollectMapper collectMapper){
        this.goodsMapper=goodsMapper;
        this.sellerMapper=sellerMapper;
        this.platformMapper=platformMapper;
        this.historyMapper = historyMapper;
        this.messageMapper=messageMapper;
        this.collectMapper = collectMapper;
    }
    public List<ProductRE> getAllGoods(){
        List<Goods> allGoods=goodsMapper.findValidGoods();
        List<ProductRE> allProducts=new ArrayList<>();
        for(int i=0;i< allGoods.size();i++){
            Goods a=allGoods.get(i);
            ProductRE p=translateGoods(a);
            allProducts.add(p);
        }
        return allProducts;
    }
    public List<ProductRE> getSellerGoods(Integer sellerId){
        List<Goods> sellerGoods = goodsMapper.findBySellerId(sellerId);
        List<ProductRE> sellerProducts=new ArrayList<>();
        for(int i=0;i<sellerGoods.size();i++){
            Goods a=sellerGoods.get(i);
            ProductRE p=translateGoods(a);
            sellerProducts.add(p);
        }
        return sellerProducts;
    }
    public ProductRE getGoodsById(Integer id){
        Goods goods=goodsMapper.findById(id);
        ProductRE p=translateGoods(goods);
        return p;
    }
    public GetGoodsRE getGoodsByIdAndUserId(Integer id,Integer userId){
        try {
            GetGoodsRE goods = goodsMapper.findGoodsByIdAndUserId(id, userId);
            return goods;
            // 处理获得的商品信息
        } catch (Exception e) {
            // 处理异常情况，比如打印异常信息或者进行其他处理
            e.printStackTrace(); // 仅作为示例，实际中可以根据具体情况处理异常
            return null;
        }
    }
    public ProductRE translateGoods(Goods goods){
        String sellerName= sellerMapper.findById(goods.getSellerId()).getName();
        String platformName= platformMapper.findById(goods.getPlatformId()).getName();
        ProductRE p=new ProductRE(goods,sellerName,platformName);
        return p;
    }
    @Transactional
    public Boolean insertGoods(InsertGoodsVO insertGoodsVO){
        try {
            Goods goods = goodsMapper.findByTagPlatformSeller(insertGoodsVO.getSellerName(), insertGoodsVO.getTag(), insertGoodsVO.getPlatformName());
            if (goods == null) {
                Boolean insertGoods = goodsMapper.insert(insertGoodsVO) == 1;
                Goods that = goodsMapper.findByTagPlatformSeller(insertGoodsVO.getSellerName(), insertGoodsVO.getTag(), insertGoodsVO.getPlatformName());
                Boolean insertHistory = historyMapper.insertHistory(that,1) == 1;
                return insertGoods && insertHistory;
            } else {
                return false;
            }
        }
        catch (Exception e) {
            // 处理异常情况，比如打印异常信息或者进行其他处理
            e.printStackTrace();
            return false;
        }
    }
    @Transactional
    public Boolean updateGoods(UpdateGoodsVO updateGoodsVO) {
        try {
            Goods goods = goodsMapper.findById(updateGoodsVO.getGoodsId());
            Goods another=goodsMapper.findByTagPlatformSeller(updateGoodsVO.getSellerName(), updateGoodsVO.getTag(), updateGoodsVO.getPlatformName());
            if(goods!=null&& (another==null ||another.getId()== updateGoodsVO.getGoodsId())){
                if(updateGoodsVO.getUserType()==1){//商家
                    Instant currentInstant = Instant.now();
                    // 创建 java.sql.Date 对象，只保留日期部分
                    Date currentDate = new Date(currentInstant.toEpochMilli());
                    List<History> histories=historyMapper.findByGoodsIdAndDate(updateGoodsVO.getGoodsId(),currentDate,updateGoodsVO.getUserType());
                    if(histories.size()!=0){
                        return false;
                    }
                }
                else if(updateGoodsVO.getUserType()==0){//管理员
                }
                else{
                    return false;
                }
                Boolean a=goodsMapper.update(updateGoodsVO)==1;
                Boolean b=true;
                if(goods.getPrice()!=updateGoodsVO.getPrice()){
                    //插入历史
                    b= historyMapper.insertHistory(goodsMapper.findById(updateGoodsVO.getGoodsId()),updateGoodsVO.getUserType())==1;
                    messageMapper.insert(goodsMapper.findById(updateGoodsVO.getGoodsId()));
                }
                return a&&b;
            }
            else{
                return false;
            }
        }
        catch (Exception e) {
            // 处理异常情况，比如打印异常信息或者进行其他处理
            e.printStackTrace();
            return false;
        }
    }

    public List<History> getHistoryPrice(Integer goodsId,Integer timeLen){
        if (timeLen == -1){
            return historyMapper.findByGoodsId(goodsId);
        }
        else {
            return historyMapper.findByGoodsIdandTime(goodsId,timeLen);
        }
    }

    @Transactional
    public Boolean deleteGoodsById(Integer goodsId){
        //在消息列表中插入删除消息，删除收藏夹里的商品相关消息，历史价格表删除，删除商品
        // 涉及修改1：获取收藏列表的时候，对于失效商品，要去message中查名字
        // 涉及修改2: 获取消息列表的时候，注意显示信息
        boolean finalSucceed = true;
        Goods goods = goodsMapper.findById(goodsId);
        List<Message> messages = messageMapper.findByGoodsId(goodsId);
        boolean succeedMessageD = messageMapper.deleteByGoodsId(goodsId)==1;
        finalSucceed = (finalSucceed && succeedMessageD);
        boolean succeedInsertD = true;

        for (Message message: messages){
            boolean succeed = messageMapper.insertDeleteMessage(goods.getName(),message.getUserId())==1;
            if(succeed==false){
                succeedInsertD = false;
                break;
            }
        }
        finalSucceed = (finalSucceed && succeedInsertD);
        boolean setUnvalid = collectMapper.setUnvalid(goodsId,goods.getName())==1;
        boolean deleteHistory = historyMapper.deleteByGoodsId(goodsId)==1;
        boolean deleteGoods = goodsMapper.deleteByGoodsId(goodsId)==1;
        finalSucceed = (finalSucceed && (setUnvalid && deleteHistory));
        finalSucceed = (finalSucceed && deleteGoods);
        return finalSucceed;

    }

}

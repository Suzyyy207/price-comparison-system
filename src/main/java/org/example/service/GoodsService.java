package org.example.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.example.mapper.*;
import org.example.model.RE.*;
import org.example.model.VO.InsertGoodsVO;
import org.example.model.VO.SearchGoodsVO;
import org.example.model.VO.SearchMessageVO;
import org.example.model.VO.UpdateGoodsVO;
import org.example.model.entity.Goods;
import org.example.model.entity.History;
import org.example.model.entity.Message;
import org.example.model.entity.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Map;

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
    @Transactional
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
    @Transactional
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
    @Transactional
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
    @Transactional
    public List<ProductRE> searchGoods(SearchGoodsVO searchGoodsVO){
        try {
            List<Map<String, Object>> executionPlan = goodsMapper.explainFindGoods(searchGoodsVO);
            System.out.println("Query Execution Plan: " + executionPlan.get(0));
            List<ProductRE> goods = goodsMapper.findGoodsByTypeAndKeyword(searchGoodsVO);
            return goods;
        }
        catch (Exception e) {
            // 处理异常情况，比如打印异常信息或者进行其他处理
            e.printStackTrace();
            return null;
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

    @Transactional
    public Boolean deleteSeller(Integer sellerId){
        //删除商品，删除商家
        List<Goods> sellerGoods = goodsMapper.findBySellerId(sellerId);
        for (Goods goods: sellerGoods){
            boolean deleteGoods = this.deleteGoodsById(goods.getId());
            if (deleteGoods == false){
                return false;
            }
        }
        boolean deleteSeller = sellerMapper.deleteBySellerId(sellerId)==1;
        return deleteSeller;
    }

    public List<TopTagCollectRE> getTop4Year(){
        List<TopTagCollectRE> topCollect= goodsMapper.getTop4Year();
        return topCollect;
    }

    public List<TopTag4SexRE> getTop4Sex(){
        List<TopTag4SexRE> topTag= goodsMapper.getTop4Sex();
        return topTag;
    }

    public List<String> getTags(String keyword){
        List<String> tags = goodsMapper.getTags(keyword);
        return tags;
    }

    public List<PriceCompareRE> getTagPrice(SearchMessageVO searchMessageVO){
        // user_id 当platform_id用,0表示全部
        List<PriceCompareRE> tagPrice;
        if(searchMessageVO.getUserId() == 0){
            tagPrice = goodsMapper.getTagPriceAll(searchMessageVO.getKeyword());
        }
        else{
            tagPrice = goodsMapper.getTagPrice(searchMessageVO.getKeyword(), searchMessageVO.getUserId());
        }

        return tagPrice;
    }
    @Transactional
    public Boolean insertGoodsFile(String filepath){
        List<String[]> data = new ArrayList<>();//列表
        try (
                // 以GBK编码读取CSV文件
                CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(filepath),"GBK"))
        ) {
            String[] line;//一行String
            while ((line = reader.readNext()) != null) {
                data.add(line);
            }
            data.remove(0);
            Boolean succeed=true;
            for (String[] l : data) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                java.util.Date utilDate = dateFormat.parse(l[8]);
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                Goods goods = new Goods(
                        Integer.parseInt(l[0]),
                        l[1],
                        l[5],
                        Double.parseDouble(l[2]),
                        Double.parseDouble(l[3]),
                        l[4],
                        Integer.parseInt(l[6]),
                        Integer.parseInt(l[7]),
                        sqlDate,
                        l[9],
                        Integer.parseInt(l[10]));
                succeed=succeed&&goodsMapper.insertFile(goods)==1;
            }
            return succeed;
        }
        catch (Exception e) {
            // 处理异常情况，比如打印异常信息或者进行其他处理
            e.printStackTrace();
            return false;
        }
    }
    @Transactional
    public Boolean insertPriceHistoryFile(String filepath){
        List<String[]> data = new ArrayList<>();//列表
        try (
                // 以GBK编码读取CSV文件
                CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(filepath),"GBK"))
        ) {
            String[] line;//一行String
            while ((line = reader.readNext()) != null) {
                data.add(line);
            }
            data.remove(0);
            Boolean succeed=true;
            for (String[] l : data) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                java.util.Date utilDate = dateFormat.parse(l[2]);
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                History history=new History(Integer.parseInt(l[0]),Double.parseDouble(l[1]),Integer.parseInt(l[3]),sqlDate);
                succeed=succeed&&historyMapper.insertHistoryFile(history)==1;
            }
            return succeed;
        }
        catch (Exception e) {
            // 处理异常情况，比如打印异常信息或者进行其他处理
            e.printStackTrace();
            return false;
        }
    }
}

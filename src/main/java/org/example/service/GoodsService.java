package org.example.service;

import org.example.mapper.GoodsMapper;
import org.example.mapper.PlatformMapper;
import org.example.mapper.SellerMapper;
import org.example.model.RE.ProductRE;
import org.example.model.VO.InsertGoodsVO;
import org.example.model.VO.UpdateGoodsVO;
import org.example.model.entity.Goods;
import org.example.model.entity.Platform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("GoodsService")
public class GoodsService {
    private final GoodsMapper goodsMapper;
    private final SellerMapper sellerMapper;
    private final PlatformMapper platformMapper;
    @Autowired
    public GoodsService(GoodsMapper goodsMapper,SellerMapper sellerMapper,PlatformMapper platformMapper){
        this.goodsMapper=goodsMapper;
        this.sellerMapper=sellerMapper;
        this.platformMapper=platformMapper;
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

    public ProductRE translateGoods(Goods goods){
        String sellerName= sellerMapper.findById(goods.getSellerId()).getName();
        String platformName= platformMapper.findById(goods.getPlatformId()).getName();
        ProductRE p=new ProductRE(goods,sellerName,platformName);
        return p;
    }
    public Boolean insertGoods(InsertGoodsVO insertGoodsVO){
        return goodsMapper.insert(insertGoodsVO)==1;
    }
    public Boolean updateGoods(UpdateGoodsVO updateGoodsVO){
        Integer id=updateGoodsVO.getId();
        Double minPrice;
        if(updateGoodsVO.getPrice()<getGoodsById(id).getMinPrice()){
            minPrice= updateGoodsVO.getPrice();
        }
        else{
            minPrice=getGoodsById(id).getMinPrice();
        }
        //!!!!!!!!!!!!!!!!!插入价格变动记录
        return goodsMapper.update(updateGoodsVO,minPrice)==1;
    }
}

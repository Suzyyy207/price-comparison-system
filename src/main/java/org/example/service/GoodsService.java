package org.example.service;

import org.example.mapper.GoodsMapper;
import org.example.mapper.PlatformMapper;
import org.example.mapper.SellerMapper;
import org.example.model.RE.ProductRE;
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
    public List<Goods> getAllGoods(){
        return goodsMapper.findValidGoods();
    }
    public List<ProductRE> getSellerGoods(Integer sellerId){
        List<Goods> sellerGoods = goodsMapper.findBySellerId(sellerId);
        List<ProductRE> sellerProducts=new ArrayList<>();
        for(int i=0;i<sellerGoods.size();i++){
            Goods a=sellerGoods.get(i);
            String sellerName= sellerMapper.findById(sellerId).getName();
            String platformName= platformMapper.findById(a.getPlatformId()).getName();
            ProductRE p=new ProductRE(a,sellerName,platformName);
            sellerProducts.add(p);
        }
        return sellerProducts;
    }
}

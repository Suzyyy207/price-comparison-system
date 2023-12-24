package org.example.service;

import org.apache.ibatis.annotations.Update;
import org.example.mapper.CollectMapper;
import org.example.mapper.SellerMapper;
import org.example.model.RE.AnalysisRE;
import org.example.model.VO.InsertVO;
import org.example.model.VO.UpdateVO;
import org.example.model.entity.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("SellerService")
public class SellerService {
    private final SellerMapper sellerMapper;
    private final CollectMapper collectMapper;
    @Autowired
    public SellerService(SellerMapper sellerMapper,CollectMapper collectMapper){
        this.sellerMapper=sellerMapper;
        this.collectMapper=collectMapper;
    }
    public Seller getSellerById(int id) {
        return sellerMapper.findById(id);
    }
    public Boolean insertSeller(InsertVO insertVO){
        return sellerMapper.insert(insertVO)==1;
    }
    public Boolean updateSeller(UpdateVO updateVO){
        return sellerMapper.update(updateVO)==1;
    }
    public List<AnalysisRE> getAvgExpectPrice(Integer sellerId){
        try{
            return collectMapper.findAverageExpectPriceByGoodsId(sellerId);
        }
        catch (Exception e) {
            // 处理异常情况，比如打印异常信息或者进行其他处理
            e.printStackTrace();
            return null;
        }
    }
}

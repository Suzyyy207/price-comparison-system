package org.example.service;

import org.apache.ibatis.annotations.Update;
import org.example.mapper.SellerMapper;
import org.example.model.VO.InsertVO;
import org.example.model.VO.UpdateVO;
import org.example.model.entity.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SellerService")
public class SellerService {
    private final SellerMapper sellerMapper;
    @Autowired
    public SellerService(SellerMapper sellerMapper){
        this.sellerMapper=sellerMapper;
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
}

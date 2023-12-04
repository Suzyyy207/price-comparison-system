package org.example.service;

import org.example.mapper.SellerMapper;
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
}

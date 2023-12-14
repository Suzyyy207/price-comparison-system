package org.example.service;

import org.example.mapper.AdminMapper;
import org.example.mapper.SellerMapper;
import org.example.mapper.UserMapper;
import org.example.model.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("AdminService")
public class AdminService {
    private final AdminMapper adminMapper;
    private final UserMapper userMapper;
    private final SellerMapper sellerMapper;
    @Autowired
    public AdminService(AdminMapper adminMapper, UserMapper userMapper, SellerMapper sellerMapper){
        this.adminMapper=adminMapper;
        this.userMapper=userMapper;
        this.sellerMapper=sellerMapper;
    }
    public Admin getAdminById(int id) {
        return adminMapper.findById(id);
    }
    public List<User> getAllUsers(){
        return userMapper.findAll();
    }
    public List<Seller> getAllSellers(){
        return sellerMapper.findAll();
    }
}

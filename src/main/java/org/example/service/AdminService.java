package org.example.service;

import org.example.mapper.AdminMapper;
import org.example.model.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AdminService")
public class AdminService {
    private final AdminMapper adminMapper;
    @Autowired
    public AdminService(AdminMapper adminMapper){
        this.adminMapper=adminMapper;
    }
    public Admin getAdminById(int id) {
        return adminMapper.findById(id);
    }
}

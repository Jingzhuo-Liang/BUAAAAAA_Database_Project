package com.example.dbprojectmain.service;

import com.example.dbprojectmain.dao.AdminDAO;
import com.example.dbprojectmain.entity.Admin;
import com.example.dbprojectmain.entity.AdminLoginBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    AdminDAO adminDao;
    
    public List<Admin> findAll() {
        return adminDao.findAll();
    }

    public int checkadmin(String adminName, String adminPwd) {
        return adminDao.checkadmin(adminName, adminPwd);
    }
}
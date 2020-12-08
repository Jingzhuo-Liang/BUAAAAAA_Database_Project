package com.example.dbprojectmain.service;


import com.example.dbprojectmain.dao.UserDAO;
import com.example.dbprojectmain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDAO userDao;

    public boolean isExist(String email) {
        return userDao.findByEmail(email) != null;
    }

    public User getByEmail(String email) {
        return userDao.findByEmail(email);
    }

    public User get(String email, String password) {
        return userDao.findByEmailAndPassword(email,password);
    }

    public void add(User user) {
        userDao.save(user);
    }

    public User getById(int id) {
        return userDao.findById(id);
    }

    public void updateUser(User user) {
        userDao.save(user);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public List<User> findByNamedParam(String title) {
        return userDao.findByNamedParam(title);
    }
}

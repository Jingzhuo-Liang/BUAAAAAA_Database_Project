package com.evan.demo.service;

import com.evan.demo.dao.UserDAO;
import com.evan.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDAO userDao;

    public boolean isExist(String username) {
        User user = getByName(username);
        return user != null;
    }

    public User getByName(String username) {
        return userDao.findByUsername(username);
    }

    public User get(String username, String password) {
        return userDao.getByUsernameAndPassword(username,password);
    }

    public void add(User user) {
        userDao.save(user);
    }
}

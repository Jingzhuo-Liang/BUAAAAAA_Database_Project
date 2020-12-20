package com.example.dbprojectmain.service;


import com.example.dbprojectmain.dao.UserDAO;
import com.example.dbprojectmain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

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

    public void deleteById(int id) {
        System.out.println(id);
        System.out.println(userDao.deleteByMyId(id));
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public List<User> findByTitleAndType(String title, String type) {
        User example = new User();
        Map<String, String> vvvip = new HashMap<>();
        vvvip.put("vip","1");
        vvvip.put("nvip","0");
        ExampleMatcher matcher = ExampleMatcher
                                .matchingAll()
                                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                                .withIgnoreCase()
                                .withIgnorePaths("id");
        if (title != null && title.isEmpty())
            title = null;
        example.setUsername(title);
        if (type != null && (type.isEmpty() || type.equals("total")))
            type = null;
        example.setIsvip(vvvip.get(type));
        System.out.println("Users example.getIsvip: ");
        System.out.println(example.getIsvip());
        System.out.println("Users example.getUsername: ");
        System.out.println(example.getUsername());
        return userDao.findAll(Example.of(example, matcher));
    }

}

package com.example.dbprojectmain.dao;


import com.example.dbprojectmain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDAO extends JpaRepository<User,Integer> {
    //User findByName(String username);

    //User findByNameAndPassword(String username, String password);

    User findByEmailAndPassword(String email, String password);

    User findById(int id);

    User findByEmail(String email);

    List<User> findAll();
}
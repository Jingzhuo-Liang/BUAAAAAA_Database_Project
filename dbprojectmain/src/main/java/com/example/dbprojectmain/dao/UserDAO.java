package com.example.dbprojectmain.dao;


import com.example.dbprojectmain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;


public interface UserDAO extends JpaRepository<User,Integer> {
    //User findByName(String username);

    //User findByNameAndPassword(String username, String password);

    User findByEmailAndPassword(String email, String password);

    User findById(int id);

    User findByEmail(String email);

    List<User> findAll();

    @Query(value = "select * from Users where Users.username like %:title%", nativeQuery = true)
    List<User> findByNamedParam(@Param("title") String title);

    @Query(value = "select * from Users where Users.isvip = :type", nativeQuery = true)
    List<User> findByVIP(@Param("type") String type);

    @Modifying
    @Transactional
    @Query(value = "delete from Users where Users.id = :id", nativeQuery = true)
    int deleteByMyId(@Param("id") int id);
}
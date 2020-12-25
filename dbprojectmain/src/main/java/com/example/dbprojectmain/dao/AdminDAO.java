package com.example.dbprojectmain.dao;

import com.example.dbprojectmain.entity.Admin;
import com.example.dbprojectmain.entity.AdminLoginBody;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.sql.Date;
import java.time.LocalDate;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import javafx.util.Pair;
import org.springframework.data.jpa.repository.query.Procedure;


public interface AdminDAO extends JpaRepository<Admin, Integer> {
    @Query(value = "call checkadmin(:adminName, :adminPwd)", nativeQuery = true)
    int checkadmin(@Param("adminName") String adminName, @Param("adminPwd") String adminPwd);
}   

package com.example.dbprojectmain.dao;

import com.example.dbprojectmain.entity.Popular;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PopularDAO extends JpaRepository<Popular, Integer> {
    @Query(value = "select id from Popular", nativeQuery = true)
    List<Integer> getAllId();
}

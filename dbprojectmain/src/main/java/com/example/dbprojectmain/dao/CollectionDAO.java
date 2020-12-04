package com.example.dbprojectmain.dao;

import com.example.dbprojectmain.entity.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollectionDAO extends JpaRepository<Collection, Integer> {
    List<Collection> findAllByUserid(int userid);
    void deleteByUseridAndComid(int userid, int comid);
}

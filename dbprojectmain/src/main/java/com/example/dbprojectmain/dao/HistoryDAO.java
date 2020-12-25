package com.example.dbprojectmain.dao;

import com.example.dbprojectmain.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryDAO extends JpaRepository<History, Integer> {

    List<History> findAllByUserid(int userid);

    void deleteByUseridAndComid(int userid, int comid);
}

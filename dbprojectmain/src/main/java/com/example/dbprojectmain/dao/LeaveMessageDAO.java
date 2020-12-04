package com.example.dbprojectmain.dao;

import com.example.dbprojectmain.entity.LeaveMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface LeaveMessageDAO extends JpaRepository<LeaveMessage, Integer> {

    List<LeaveMessage> findAllByComid(int comid);
}

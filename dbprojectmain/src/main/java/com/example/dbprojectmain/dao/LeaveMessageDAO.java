package com.example.dbprojectmain.dao;

import com.example.dbprojectmain.entity.LeaveMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;


public interface LeaveMessageDAO extends JpaRepository<LeaveMessage, Integer> {

    List<LeaveMessage> findAllByComid(int comid);

    @Modifying
    @Transactional
    @Query(value = "delete from LeaveMessage where LeaveMessage.id = :id", nativeQuery = true)
    int deleteByMyId(@Param("id") int id);
}

package com.example.dbprojectmain.dao;

import com.example.dbprojectmain.entity.ReplyMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;


public interface ReplyMessageDAO extends JpaRepository<ReplyMessage, Integer> {
    List<ReplyMessage> findAllByLeavemessageid(int id);

    @Modifying
    @Transactional
    @Query(value = "delete from ReplyMessage where ReplyMessage.id = :id", nativeQuery = true)
    int deleteByMyId(@Param("id") int id);
}

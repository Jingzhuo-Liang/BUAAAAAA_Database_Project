package com.example.dbprojectmain.dao;

import com.example.dbprojectmain.entity.ReplyMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ReplyMessageDAO extends JpaRepository<ReplyMessage, Integer> {
    List<ReplyMessage> findAllByLeavemessageid(int id);
}

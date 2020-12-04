package com.example.dbprojectmain.service;

import com.example.dbprojectmain.dao.LeaveMessageDAO;
import com.example.dbprojectmain.dao.ReplyMessageDAO;
import com.example.dbprojectmain.entity.LeaveMessage;
import com.example.dbprojectmain.entity.ReplyMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    LeaveMessageDAO leaveMessageDAO;

    @Autowired
    ReplyMessageDAO replyMessageDAO;

    public List<LeaveMessage> getAllLeaveMessage(int comid) {
        return leaveMessageDAO.findAllByComid(comid);
    }

    public List<ReplyMessage> getAllReplyMessage(int leaveMessageId) {
        return replyMessageDAO.findAllByLeavemessageid(leaveMessageId);
    }

    public void AddLeaveMessage(LeaveMessage leaveMessage) {
        leaveMessageDAO.save(leaveMessage);
    }

    public void AddReplyMessage(ReplyMessage replyMessage) {
        replyMessageDAO.save(replyMessage);
    }
}

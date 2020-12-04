package com.example.dbprojectmain.controller;

import com.example.dbprojectmain.dao.UserDAO;
import com.example.dbprojectmain.entity.LeaveMessage;
import com.example.dbprojectmain.entity.ReplyMessage;
import com.example.dbprojectmain.result.Result;
import com.example.dbprojectmain.result.ResultFactory;
import com.example.dbprojectmain.service.MessageService;
import com.example.dbprojectmain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MessageController {

    @Autowired
    MessageService messageService;
    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping("/api/leavemessage/{comid}")
    @ResponseBody
    public Result getAllLeaveMesssage(@PathVariable("comid") int comid) {
        List<LeaveMessage> leaveMessages = messageService.getAllLeaveMessage(comid);
        for (int i = 0;i < leaveMessages.size(); i++) {
            leaveMessages.get(i).setUsername(userService.getById(leaveMessages.get(i).getUserid()).getUsername());
        }
        return ResultFactory.buildSuccessFactory(leaveMessages);
    }

    @CrossOrigin
    @PostMapping("/api/replymessage/{id}")
    @ResponseBody
    public Result getAllReplyMesssage(@PathVariable("id") int id) {
        List<ReplyMessage> replyMessages = messageService.getAllReplyMessage(id);
        for (int i = 0;i < replyMessages.size(); i++) {
            replyMessages.get(i).setUsername(userService.getById(replyMessages.get(i).getUserid()).getUsername());
        }
        return ResultFactory.buildSuccessFactory(replyMessages);
    }

    @CrossOrigin
    @PostMapping("/api/addLeaveMessage")
    @ResponseBody
    public Result addLeaveMessage(@RequestBody LeaveMessage leaveMessage) {
        /*
        System.out.println(leaveMessage.getId());
        System.out.println(leaveMessage.getUserid());
        System.out.println(leaveMessage.getComid());
        System.out.println(leaveMessage.getTime());
        System.out.println(leaveMessage.getMessage());
         */
        messageService.AddLeaveMessage(leaveMessage);
        return ResultFactory.buildSuccessFactory("留言成功");
    }

    @CrossOrigin
    @PostMapping("/api/addReplyMessage")
    @ResponseBody
    public Result addReplyMessage(@RequestBody ReplyMessage replyMessage) {
        messageService.AddReplyMessage(replyMessage);
        return ResultFactory.buildSuccessFactory("回复成功");
    }
}

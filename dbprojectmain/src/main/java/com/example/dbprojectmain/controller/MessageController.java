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

import java.util.Collections;

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

    @CrossOrigin
    @GetMapping("/api/leavemessage/list")
    @ResponseBody
    public Result getMessageList(@RequestParam(defaultValue = "id") 
                                String sort, 
                                @RequestParam(required = false) 
                                Integer commidtyid) {
        List<LeaveMessage> ans;
        // Sort by username and vip
        if (commidtyid != null)
            ans = messageService.getAllLeaveMessage(commidtyid.intValue());
        else
            ans = messageService.findAll();

        // Sort by inverse or not
        if (sort.equals("-id")) {
            Collections.reverse(ans);
        }
        return ResultFactory.buildSuccessFactory(ans);
    }

    @CrossOrigin
    @PostMapping("/api/leavemessage/update")
    @ResponseBody
    public Result addOrUpdate(@RequestBody LeaveMessage updatelist) {
        String message;
        messageService.AddLeaveMessage(updatelist);
        message = "update successful";
        return ResultFactory.buildSuccessFactory(message);
    }

    @CrossOrigin
    @PostMapping("/api/leavemessage/delete")
    @ResponseBody
    public Result Delete(@RequestBody LeaveMessage deletelist) {
        String message;
        messageService.deleteById(deletelist.getId());
        message = "delete successful";
        return ResultFactory.buildSuccessFactory(message);
    }

    @CrossOrigin
    @GetMapping("/api/replymessage/list")
    @ResponseBody
    public Result getReplyMessageList(@RequestParam(defaultValue = "id") 
                                String sort, 
                                @RequestParam(required = false) 
                                Integer leavemsgid) {
        List<ReplyMessage> ans;
        // Sort by username and vip
        if (leavemsgid != null)
            ans = messageService.getAllReplyMessage(leavemsgid.intValue());
        else
            ans = messageService.replyfindAll();

        // Sort by inverse or not
        if (sort.equals("-id")) {
            Collections.reverse(ans);
        }
        return ResultFactory.buildSuccessFactory(ans);
    }

    @CrossOrigin
    @PostMapping("/api/replymessage/update")
    @ResponseBody
    public Result addOrUpdateReply(@RequestBody ReplyMessage updatelist) {
        String message;
        messageService.AddReplyMessage(updatelist);
        message = "update successful";
        return ResultFactory.buildSuccessFactory(message);
    }

    @CrossOrigin
    @PostMapping("/api/replymessage/delete")
    @ResponseBody
    public Result DeleteReply(@RequestBody LeaveMessage deletelist) {
        String message;
        messageService.deleteByReplyId(deletelist.getId());
        message = "delete successful";
        return ResultFactory.buildSuccessFactory(message);
    }

}

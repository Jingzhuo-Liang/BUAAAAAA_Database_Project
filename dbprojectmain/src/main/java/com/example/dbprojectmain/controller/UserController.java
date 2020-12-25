package com.example.dbprojectmain.controller;

import com.example.dbprojectmain.entity.User;
import com.example.dbprojectmain.result.Result;
import com.example.dbprojectmain.result.ResultFactory;
import com.example.dbprojectmain.service.UserService;
import org.apache.catalina.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.HtmlUtils;

import javax.security.auth.Subject;
import javax.servlet.http.HttpSession;

import java.util.List;
import java.util.Collections;


@Controller
public class UserController {

    @Autowired
    UserService userService;

    @CrossOrigin
    @GetMapping("/api/user/list")
    @ResponseBody
    public Result getUserList(@RequestParam(defaultValue = "id") 
                                String sort, 
                                @RequestParam(required = false) 
                                String title,
                                @RequestParam(required = false) 
                                String type) {
        List<User> ans;
        // Sort by username and vip
        ans = userService.findByTitleAndType(title, type);

        // Sort by inverse or not
        if (sort.equals("-id")) {
            Collections.reverse(ans);
        }
        return ResultFactory.buildSuccessFactory(ans);
    }

    @CrossOrigin
    @PostMapping("/api/user/update")
    @ResponseBody
    public Result addOrUpdate(@RequestBody User updatelist) {
        String message;
        userService.add(updatelist);
        message = "update successful";
        return ResultFactory.buildSuccessFactory(message);
    }

    @CrossOrigin
    @PostMapping("/api/user/delete")
    @ResponseBody
    public Result Delete(@RequestBody User deletelist) {
        String message;
        userService.deleteById(deletelist.getId());
        message = "delete successful";
        return ResultFactory.buildSuccessFactory(message);
    }
}
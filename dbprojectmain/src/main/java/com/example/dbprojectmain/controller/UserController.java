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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import javax.security.auth.Subject;
import javax.servlet.http.HttpSession;


@Controller
public class UserController {

    @Autowired
    UserService userService;

    @CrossOrigin
    @GetMapping("/api/user/list")
    @ResponseBody
    public Result getUserList() {
        return ResultFactory.buildSuccessFactory(userService.findAll());
    }

}
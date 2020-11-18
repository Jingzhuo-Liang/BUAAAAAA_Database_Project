package com.evan.demo.controller;

import com.evan.demo.result.Result;
import com.evan.demo.result.ResultFactory;
import com.evan.demo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import com.evan.demo.pojo.User;

import javax.servlet.http.HttpSession;


@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping("api/login")
    @ResponseBody
    public Result login(@RequestBody User requestUser) {
        // 对 html 标签进行转义，防止 XSS 攻击
        String username = requestUser.getUsername();
        //username = HtmlUtils.htmlEscape(username);
        Subject subject = SecurityUtils.getSubject();
        //User user = userService.get(username, requestUser.getPassword());
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, requestUser.getPassword());
        /*
        if (null == user) {
            return new Result(400);
        }
        else {
            session.setAttribute("user",user);
            return new Result(200);
        }
         */
        try{
            subject.login(usernamePasswordToken);
            return ResultFactory.buildSuccessFactory(username);
        } catch (AuthenticationException e) {
            String message = "账号密码错误";
            return ResultFactory.buildFailFactory(message);
        }
    }
}
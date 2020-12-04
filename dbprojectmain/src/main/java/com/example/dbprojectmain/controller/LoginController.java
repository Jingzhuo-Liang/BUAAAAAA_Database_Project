package com.example.dbprojectmain.controller;

import com.example.dbprojectmain.entity.User;
import com.example.dbprojectmain.result.Result;
import com.example.dbprojectmain.result.ResultFactory;
import com.example.dbprojectmain.service.UserService;
import org.apache.catalina.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;
import sun.plugin.dom.core.Element;

import javax.security.auth.Subject;
import javax.servlet.http.HttpSession;


@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping("api/updateUser")
    @ResponseBody
    public Result editUser(@RequestBody User user) {
        if (user.getPassword().length() == 0) {
            user.setPassword(userService.getById(user.getId()).getPassword());

        }
        userService.updateUser(user);
        return ResultFactory.buildSuccessFactory(user);
    }

    @CrossOrigin
    @PostMapping("api/register")
    @ResponseBody
    public Result register(@RequestBody User requestUser) {
        String email = requestUser.getEmail();
        email = HtmlUtils.htmlEscape(email);
        /*
        System.out.println(email);
        System.out.println(requestUser.getUsername());
        System.out.println(requestUser.getIsmember());
        System.out.println(requestUser.getIsvip());
        System.out.println(requestUser.getDate());
         */
        String message;
        if (userService.isExist(email)) { //有重复邮箱
            message = "邮箱已被注册";
            return ResultFactory.buildFailFactory(message);
        }
        userService.add(requestUser);
        message = "注册成功";
        return ResultFactory.buildSuccessFactory(message);
    }


    @CrossOrigin
    @PostMapping("api/login")
    @ResponseBody
    public Result login(@RequestBody User requestUser, HttpSession session) {
        // 对 html 标签进行转义，防止 XSS 攻击
        String email = requestUser.getEmail();
        email = HtmlUtils.htmlEscape(email);
        //Subject subject = SecurityUtils.getSubject();
        User user = userService.get(email, requestUser.getPassword());
        //UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, requestUser.getPassword());

        if (null == user) {
            return ResultFactory.buildFailFactory("登录失败");
        }
        else {
            session.setAttribute("user", user);
            return ResultFactory.buildSuccessFactory(user);
        }
        /*
        try{
            subject.login(usernamePasswordToken);
            return ResultFactory.buildSuccessFactory(username);
        } catch (AuthenticationException e) {
            String message = "账号密码错误";
            return ResultFactory.buildFailFactory(message);
        }

         */
    }
}
package com.evan.demo.controller;

import com.evan.demo.pojo.User;
import com.evan.demo.result.Result;
import com.evan.demo.result.ResultFactory;
import com.evan.demo.service.UserService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

@Controller
public class RegisterController {

    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping("/api/register")
    @ResponseBody
    public Result register(@RequestBody User user) {
        String userName = user.getUsername();
        String password = user.getPassword();
        //将特殊字符转义
        userName = HtmlUtils.htmlEscape(userName);
        user.setUsername(userName);
        boolean exist = userService.isExist(userName);
        if (exist) {
            String message = "用户名已被使用";
            return ResultFactory.buildFailFactory(message);
        }

        // 生成盐
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        //设置hash，算法迭代次数
        int times = 2;
        //得到hash后的密码
        String encodePassword = new SimpleHash("md5",password,salt,times).toString();
        //存储用户信息，包括salt与hash后的密码
        user.setSalt(salt);
        user.setPassword(encodePassword);
        userService.add(user);
        return ResultFactory.buildSuccessFactory(user);
    }
}

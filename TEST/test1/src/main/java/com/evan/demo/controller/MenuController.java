package com.evan.demo.controller;

import com.evan.demo.pojo.AdminMenu;
import com.evan.demo.pojo.User;
import com.evan.demo.result.Result;
import com.evan.demo.result.ResultFactory;
import com.evan.demo.service.AdminMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MenuController {

    @Autowired
    private AdminMenuService adminMenuService;

    @CrossOrigin
    @PostMapping("/api/menu")
    @ResponseBody
    public Result menu(@RequestBody User requestUser) {
        List<AdminMenu> am = adminMenuService.getMenusByCurrentUser(requestUser);
        for (int i = 0;i < am.size();i++) {
            System.out.println(am.get(i).getId());
        }
        return ResultFactory.buildSuccessFactory(am);
        //return adminMenuService.getMenusByCurrentUser();
    }
}

package com.example.dbprojectmain.controller;

import com.example.dbprojectmain.entity.Admin;
import com.example.dbprojectmain.entity.AdminLoginBody;
import com.example.dbprojectmain.result.Result;
import com.example.dbprojectmain.result.ResultFactory;
import com.example.dbprojectmain.service.AdminService;
import org.apache.catalina.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.http.MediaType;
import javax.security.auth.Subject;
import javax.servlet.http.HttpSession;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

@Controller
public class AdminController {

    @Autowired
    AdminService adminService;

    @CrossOrigin
    @GetMapping("/api/admin/list")
    @ResponseBody
    public Result getAdminList() {
        List<Admin> ans = adminService.findAll();
        // System.out.println(Arrays.toString(ans.toArray()));
        return ResultFactory.buildSuccessFactory(ans);
    }

    @CrossOrigin
    @PostMapping(
        value = "/api/admin/login",
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Result adminLogin(@RequestBody AdminLoginBody adminLoginBody) {
        
        String username = adminLoginBody.getUsername();
        String password = adminLoginBody.getPassword();
        int enabled = adminService.checkadmin(username, password);
        
        Map<Integer, String> gettoken = new HashMap<>();
        gettoken.put(1, "admin-token");
        gettoken.put(0, "editor-token");
        if (enabled < 0) {
            return ResultFactory.buildFailFactory("Login Failed");
        }
        else {
            return ResultFactory.buildSuccessFactory(gettoken.get(enabled));
        }
    }
}
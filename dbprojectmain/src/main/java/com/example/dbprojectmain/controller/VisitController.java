package com.example.dbprojectmain.controller;

import com.example.dbprojectmain.entity.User;
import com.example.dbprojectmain.entity.Visit;
import com.example.dbprojectmain.result.Result;
import com.example.dbprojectmain.result.ResultFactory;
import com.example.dbprojectmain.service.UserService;
import com.example.dbprojectmain.service.VisitService;
import com.example.dbprojectmain.entity.VvAnswer;
import org.apache.catalina.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;
import org.springframework.web.bind.annotation.RequestParam;

import javax.security.auth.Subject;
import javax.servlet.http.HttpSession;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javafx.util.Pair;
import java.util.List;

@Controller
public class VisitController {

    @Autowired
    VisitService visitService;

    @CrossOrigin
    @GetMapping("/api/visit/list")
    @ResponseBody
    public Result getVisitList(@RequestParam(defaultValue = "7") 
                                int period) {
        var today = LocalDate.now();
        var beginDay = today.minusDays(period);
        List<VvAnswer> ans = visitService.vv(beginDay);
        return ResultFactory.buildSuccessFactory(ans);
    }
}
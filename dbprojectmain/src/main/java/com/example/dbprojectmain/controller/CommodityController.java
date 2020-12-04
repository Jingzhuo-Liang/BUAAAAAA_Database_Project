package com.example.dbprojectmain.controller;

import com.example.dbprojectmain.entity.Commodity;
import com.example.dbprojectmain.result.Result;
import com.example.dbprojectmain.result.ResultFactory;
import com.example.dbprojectmain.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CommodityController {

    @Autowired
    CommodityService commodityService;

    @CrossOrigin
    @GetMapping("/api/popular")
    @ResponseBody
    public Result getPopulars() {
        List<Commodity> populars = commodityService.getAllPopulars();
        return ResultFactory.buildSuccessFactory(populars);
    }

    @CrossOrigin
    @GetMapping("/api/commodities")
    @ResponseBody
    public Result getCommodity() {
        List<Commodity> commodities = commodityService.getAllCommodity();
        return ResultFactory.buildSuccessFactory(commodities);
    }

    @CrossOrigin
    @GetMapping("/api/search/{keywords}")
    @ResponseBody
    public Result getSearchCommodities(@PathVariable("keywords") String keywords) {
        List<Commodity> commodities = commodityService.getSearch(keywords);
        return ResultFactory.buildSuccessFactory(commodities);
    }

    // 0为默认值  1为手机 2为电脑 3为耳机
    @CrossOrigin
    @GetMapping("/api/category/{cid}")
    @ResponseBody
    public Result getCommoditiesByCategory(@PathVariable("cid") int cid) {
        List<Commodity> commodities = commodityService.getCommoditiesByCategory(cid);
        return ResultFactory.buildSuccessFactory(commodities);
    }
}

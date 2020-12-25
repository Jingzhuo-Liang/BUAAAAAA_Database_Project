package com.example.dbprojectmain.controller;

import com.example.dbprojectmain.entity.History;
import com.example.dbprojectmain.entity.Commodity;
import com.example.dbprojectmain.entity.Collection;
import com.example.dbprojectmain.result.Result;
import com.example.dbprojectmain.result.ResultFactory;
import com.example.dbprojectmain.service.HisAndColService;
import com.example.dbprojectmain.service.CommodityService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HisAndColController {

    @Autowired
    private HisAndColService hisAndColService;
    @Autowired
    private CommodityService commodityService;

    @CrossOrigin
    @PostMapping("/api/getAllHistories")
    @ResponseBody
    public Result getAllHistories(@RequestBody History temp) {
        List<History> histories = hisAndColService.getAllHistories(temp.getUserid());
        List<Commodity> commodities = new ArrayList<>();
        for (History history:histories) {
            Commodity commodity = commodityService.getCommodity(history.getComid());
            commodity.setTime(history.getTime());
            commodities.add(commodity);
        }

        return ResultFactory.buildSuccessFactory(commodities);
    }

    @CrossOrigin
    @PostMapping("/api/getAllCollections")
    @ResponseBody
    public Result getAllCollections(@RequestBody Collection temp) {
        List<Collection> collections = hisAndColService.getAllCollections(temp.getUserid());
        List<Commodity> commodities  = new ArrayList<>();
        for (Collection collection:collections) {
            Commodity commodity = commodityService.getCommodity(collection.getComid());
            commodity.setTime(collection.getTime());
            commodities.add(commodity);
        }

        return ResultFactory.buildSuccessFactory(commodities);
    }

    @CrossOrigin
    @PostMapping("/api/addHistory")
    @ResponseBody
    public Result addHistory(@RequestBody History history) {
        hisAndColService.addHistory(history);
        return ResultFactory.buildSuccessFactory("success");
    }

    @CrossOrigin
    @PostMapping("/api/addCollection")
    @ResponseBody
    public Result addCollection(@RequestBody Collection collection) {
        hisAndColService.addCollection(collection);
        return ResultFactory.buildSuccessFactory("success");
    }

    @CrossOrigin
    @PostMapping("/api/delHistory")
    @ResponseBody
    public Result delHistory(@RequestBody History history) {
        hisAndColService.delHistory(history.getUserid(),history.getComid());
        return ResultFactory.buildSuccessFactory("success");
    }

    @CrossOrigin
    @PostMapping("/api/delCollection")
    @ResponseBody
    public Result delCollection(@RequestBody Collection collection) {
        System.out.println(collection.getUserid());
        System.out.println(collection.getComid());
        hisAndColService.delCollection(collection.getUserid(), collection.getComid());
        return ResultFactory.buildSuccessFactory("success");
    }

    @CrossOrigin
    @PostMapping("/api/delAllCollections")
    @ResponseBody
    public Result delAllCollections(@RequestBody Collection temp) {
        List<Collection> collections = hisAndColService.getAllCollections(temp.getUserid());
        for (Collection collection:collections) {
            hisAndColService.delCollection(temp.getUserid(),collection.getComid());
        }
        return ResultFactory.buildSuccessFactory("success");
    }

    @CrossOrigin
    @PostMapping("/api/delAllHistories")
    @ResponseBody
    public Result delAllHistories(@RequestBody History temp) {
        List<History> histories = hisAndColService.getAllHistories(temp.getUserid());
        for (History history:histories) {
            hisAndColService.delHistory(temp.getUserid(),history.getComid());
        }
        return ResultFactory.buildSuccessFactory("success");
    }
}

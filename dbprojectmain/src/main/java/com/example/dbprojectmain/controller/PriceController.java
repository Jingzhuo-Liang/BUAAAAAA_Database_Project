package com.example.dbprojectmain.controller;

import com.example.dbprojectmain.entity.Price;
import com.example.dbprojectmain.result.Result;
import com.example.dbprojectmain.result.ResultFactory;
import com.example.dbprojectmain.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PriceController {

    @Autowired
    PriceService priceService;

    @CrossOrigin
    @PostMapping("/api/price/{id}")
    @ResponseBody
    public Result getAllPrice(@PathVariable("id") int id) {
        List<Price> prices = priceService.findAllPriceByComid(id);
        return ResultFactory.buildSuccessFactory(prices);
    }
}

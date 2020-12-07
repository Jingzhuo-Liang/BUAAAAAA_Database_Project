package com.example.dbprojectmain.controller;

import com.example.dbprojectmain.common.Discount;
import com.example.dbprojectmain.entity.CommodityDiscount;
import com.example.dbprojectmain.entity.PlatformDiscount;
import com.example.dbprojectmain.result.Result;
import com.example.dbprojectmain.result.ResultFactory;
import com.example.dbprojectmain.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DiscountController {
    @Autowired
    private DiscountService discountService;

    @CrossOrigin
    @PostMapping("/api/getCommodityDiscount/{comid}")
    @ResponseBody
    public Result getCommodityDiscount(@PathVariable("comid") int comid) {
        List<CommodityDiscount> commodityDiscounts = discountService.getCommodityDiscount(comid);
        ArrayList<Discount> discounts = new ArrayList<>();
        for(CommodityDiscount commodityDiscount:commodityDiscounts) {
            Discount discount = new Discount(commodityDiscount.getDiscount());
            discount.setTime(commodityDiscount.getTime());
            discount.setIsMember(0);
            discounts.add(discount);
        }
        return ResultFactory.buildSuccessFactory(discounts);
    }

    @CrossOrigin
    @PostMapping("/api/getPlatformDiscount/{platform}")
    @ResponseBody
    public Result getPlatformDiscount(@PathVariable("platform") int platform) {
        List<PlatformDiscount> platformDiscounts = discountService.getPlatformDiscount(platform);
        ArrayList<Discount> discounts = new ArrayList<>();
        for(PlatformDiscount platformDiscount:platformDiscounts) {
            Discount discount = new Discount(platformDiscount.getDiscount());
            discount.setTime(platformDiscount.getTime());
            discount.setIsMember(0);
            discounts.add(discount);

            discount = new Discount(platformDiscount.getMemberdiscount());
            discount.setTime(platformDiscount.getTime());
            discount.setIsMember(1);
            discounts.add(discount);
        }
        return ResultFactory.buildSuccessFactory(discounts);
    }
}

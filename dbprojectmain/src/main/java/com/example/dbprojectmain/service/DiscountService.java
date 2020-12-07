package com.example.dbprojectmain.service;

import com.example.dbprojectmain.dao.CommodityDiscountDAO;
import com.example.dbprojectmain.dao.PlatformDiscountDAO;
import com.example.dbprojectmain.entity.CommodityDiscount;
import com.example.dbprojectmain.entity.PlatformDiscount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountService {
    @Autowired
    private CommodityDiscountDAO commodityDiscountDAO;
    @Autowired
    private PlatformDiscountDAO platformDiscountDAO;

    public List<CommodityDiscount> getCommodityDiscount(int comid) {
        return commodityDiscountDAO.findAllByComid(comid);
    }

    public List<PlatformDiscount> getPlatformDiscount(int platform) {
        return platformDiscountDAO.findAllByPlatformid(platform);
    }
}

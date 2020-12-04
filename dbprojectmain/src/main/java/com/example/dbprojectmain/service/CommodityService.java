package com.example.dbprojectmain.service;

import com.example.dbprojectmain.dao.CommodityDAO;
import com.example.dbprojectmain.dao.PopularDAO;
import com.example.dbprojectmain.entity.Commodity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommodityService {

    @Autowired
    CommodityDAO commodityDAO;
    @Autowired
    PopularDAO popularDAO;

    public List<Commodity> getAllCommodity() {
        return commodityDAO.findAll();
    }

    public List<Commodity> getAllByComname(String comname) {
        return commodityDAO.findAllByComname(comname);
    }

    public Commodity getCommodity(int id) {
        return commodityDAO.findById(id);
    }

    public List<Commodity> getAllPopulars() {
        List<Integer> ids = popularDAO.getAllId();
        List<Commodity> populars = new ArrayList<>();
        for (Integer id:ids) {
            populars.add(getCommodity(id));
        }
        return populars;
    }

    public List<Commodity> getSearch(String keywords) {
        int platform = 0;
        if (keywords.contains("京东")) {
            platform = 1;
        }
        else if (keywords.contains("淘宝")) {
            platform = 2;
        }
        else if (keywords.contains("苏宁")) {
            platform = 3;
        }
        return commodityDAO.findByComnameLikeOrStorenameLikeOrGiftLikeOrPlatformLike(
                "%" + keywords + "%","%" + keywords + "%","%" + keywords + "%",
                platform);
    }

    public List<Commodity> getCommoditiesByCategory(int cid) {
        return commodityDAO.findAllByCid(cid);
    }
}

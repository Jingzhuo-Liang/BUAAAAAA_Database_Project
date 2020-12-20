package com.example.dbprojectmain.service;

import com.example.dbprojectmain.dao.CommodityDAO;
import com.example.dbprojectmain.dao.PopularDAO;
import com.example.dbprojectmain.entity.Commodity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

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

    public List<Commodity> findByName(String comname, Integer type) {
        Commodity example = new Commodity();
        ExampleMatcher matcher = ExampleMatcher.matching()
                                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                                .withIgnoreCase()
                                .withIgnorePaths("id", "favorrate", "sales", "newestprice", "cid");
        if (comname != null && comname.isEmpty())
            comname = null;
        example.setComname(comname);
        if (type != null && !type.equals(0))
            example.setPlatform(type);
        else
            matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase()
                .withIgnorePaths("platform", "id", "favorrate", "sales", "newestprice", "cid");
        System.out.println("Commodity example: ");
        System.out.println(example.getPlatform());
        return commodityDAO.findAll(Example.of(example, matcher));
    }
}

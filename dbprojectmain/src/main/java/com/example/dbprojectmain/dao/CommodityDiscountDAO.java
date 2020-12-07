package com.example.dbprojectmain.dao;

import com.example.dbprojectmain.entity.CommodityDiscount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommodityDiscountDAO extends JpaRepository<CommodityDiscount,Integer> {

    List<CommodityDiscount> findAllByComid(int comid);
}

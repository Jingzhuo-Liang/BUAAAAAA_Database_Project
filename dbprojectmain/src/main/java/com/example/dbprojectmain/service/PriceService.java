package com.example.dbprojectmain.service;

import com.example.dbprojectmain.dao.PriceDAO;
import com.example.dbprojectmain.entity.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceService {

    @Autowired
    PriceDAO priceDAO;

    public List<Price> findAllPriceByComid(int id) {
        return priceDAO.findAllByComid(id);
    }
}

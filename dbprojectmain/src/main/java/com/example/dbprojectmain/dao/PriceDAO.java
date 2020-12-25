package com.example.dbprojectmain.dao;

import com.example.dbprojectmain.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PriceDAO extends JpaRepository<Price, Integer> {

    List<Price> findAllByComid(int id);
}

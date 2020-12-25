package com.example.dbprojectmain.dao;

import com.example.dbprojectmain.entity.PlatformDiscount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlatformDiscountDAO extends JpaRepository<PlatformDiscount, Integer> {

    List<PlatformDiscount> findAllByPlatformid(int platformid);

}

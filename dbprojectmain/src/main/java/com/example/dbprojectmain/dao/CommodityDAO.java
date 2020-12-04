package com.example.dbprojectmain.dao;

import com.example.dbprojectmain.entity.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommodityDAO extends JpaRepository<Commodity, Integer> {

    Commodity findById(int id);

    List<Commodity> findAllByComname(String comname);

    List<Commodity> findByComnameLikeOrStorenameLikeOrGiftLikeOrPlatformLike(String comname, String storename,
                                                                            String gift, int platform);
    List<Commodity> findAllByCid(int cid);
}

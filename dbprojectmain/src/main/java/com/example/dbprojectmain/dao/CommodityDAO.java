package com.example.dbprojectmain.dao;

import com.example.dbprojectmain.entity.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;

public interface CommodityDAO extends JpaRepository<Commodity, Integer> {

    Commodity findById(int id);

    List<Commodity> findAllByComname(String comname);

    List<Commodity> findByComnameLikeOrStorenameLikeOrGiftLikeOrPlatformLike(String comname, String storename,
                                                                            String gift, int platform);
    List<Commodity> findAllByCid(int cid);

    @Modifying
    @Transactional
    @Query(value = "delete from Commodity where Commodity.id = :id", nativeQuery = true)
    int deleteByMyId(@Param("id") int id);
}

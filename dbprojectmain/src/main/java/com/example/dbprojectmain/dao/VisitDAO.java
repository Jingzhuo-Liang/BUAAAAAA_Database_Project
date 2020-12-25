package com.example.dbprojectmain.dao;

import com.example.dbprojectmain.entity.Visit;
import com.example.dbprojectmain.entity.VvAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.sql.Date;
import java.time.LocalDate;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import javafx.util.Pair;
import org.springframework.data.jpa.repository.query.Procedure;


public interface VisitDAO extends JpaRepository<Visit, Integer> {
    @Query(value = "call vv(:beginDate)", nativeQuery = true)
    List<VvAnswer> vv(@Param("beginDate") Date beginDay);
}

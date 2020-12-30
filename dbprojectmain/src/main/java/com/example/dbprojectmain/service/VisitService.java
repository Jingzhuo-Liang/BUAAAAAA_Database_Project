package com.example.dbprojectmain.service;


import com.example.dbprojectmain.dao.VisitDAO;
import com.example.dbprojectmain.entity.Visit;
import com.example.dbprojectmain.entity.VvAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import javafx.util.Pair;

@Service
public class VisitService {
    @Autowired
    VisitDAO visitDao;
    
    public void saveVisit(Visit visit) {
        visitDao.save(visit);
    }

    public List<VvAnswer> vv(LocalDate beginDay) {
        System.out.println(beginDay.toString());
        return visitDao.vv(Date.valueOf(beginDay));
    }
}
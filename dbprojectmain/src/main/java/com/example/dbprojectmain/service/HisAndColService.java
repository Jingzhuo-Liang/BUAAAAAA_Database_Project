package com.example.dbprojectmain.service;

import com.example.dbprojectmain.dao.HistoryDAO;
import com.example.dbprojectmain.dao.CollectionDAO;
import com.example.dbprojectmain.entity.History;
import com.example.dbprojectmain.entity.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HisAndColService {

    @Autowired
    private HistoryDAO historyDAO;

    @Autowired
    private CollectionDAO collectionDAO;

    public List<History> getAllHistories(int userid) {
        return historyDAO.findAllByUserid(userid);
    }

    public List<Collection> getAllCollections(int userid) {
        return collectionDAO.findAllByUserid(userid);
    }

    public void addHistory(History history) {
        historyDAO.save(history);
    }

    public void addCollection(Collection collection) {
        collectionDAO.save(collection);
    }

    @Transactional
    public void delHistory(int userid, int comid) {
        historyDAO.deleteByUseridAndComid(userid,comid);
    }

    @Transactional
    public void delCollection(int userid, int comid) {
        collectionDAO.deleteByUseridAndComid(userid, comid);
    }

}

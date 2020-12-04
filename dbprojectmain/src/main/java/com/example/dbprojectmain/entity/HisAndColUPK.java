package com.example.dbprojectmain.entity;

import java.io.Serializable;

public class HisAndColUPK implements Serializable {
    private int comid;

    private int userid;

    public int getComid() {
        return comid;
    }

    public void setComid(int comid) {
        this.comid = comid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}

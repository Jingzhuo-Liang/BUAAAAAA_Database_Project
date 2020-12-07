package com.example.dbprojectmain.entity;

import java.io.Serializable;

public class CommodityDiscountUPK implements Serializable {
    private int comid;
    private String time;

    public int getComid() {
        return comid;
    }

    public void setComid(int comid) {
        this.comid = comid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

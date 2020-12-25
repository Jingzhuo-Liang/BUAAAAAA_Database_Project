package com.example.dbprojectmain.entity;

import java.io.Serializable;

public class PlatformDiscountUPK implements Serializable {

    private int platformid;
    private String time;

    public int getPlatformid() {
        return platformid;
    }

    public void setPlatformid(int platformid) {
        this.platformid = platformid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

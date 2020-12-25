package com.example.dbprojectmain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "platformdiscount")
@IdClass(PlatformDiscountUPK.class)
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class PlatformDiscount {

    @Id
    private int platformid;
    @Id
    private String time;
    private String discount;
    private String memberdiscount;

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

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getMemberdiscount() {
        return memberdiscount;
    }

    public void setMemberdiscount(String memberdiscount) {
        this.memberdiscount = memberdiscount;
    }
}

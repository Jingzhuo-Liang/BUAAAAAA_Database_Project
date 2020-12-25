package com.example.dbprojectmain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "commoditydiscount")
@IdClass(CommodityDiscountUPK.class)
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class CommodityDiscount {

    @Id
    private int comid;
    @Id
    private String time;
    private String discount;

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

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }
}

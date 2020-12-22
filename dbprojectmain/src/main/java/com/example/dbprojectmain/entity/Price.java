package com.example.dbprojectmain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "price")
@IdClass(PriceUPK.class)
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Price {

    @Id
    @Column(name = "comid")
    private int comid;
    @Id
    @Column(name = "date")
    private String date;
    private int price;

    public int getComid() {
        return comid;
    }

    public void setComid(int comid) {
        this.comid = comid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}

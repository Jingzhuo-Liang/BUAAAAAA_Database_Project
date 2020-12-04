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
    private int comdisc;
    private int platdisc;
    private int studisc;
    private int memdisc;

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

    public int getComdisc() {
        return comdisc;
    }

    public void setComdisc(int comdisc) {
        this.comdisc = comdisc;
    }

    public int getPlatdisc() {
        return platdisc;
    }

    public void setPlatdisc(int platdisc) {
        this.platdisc = platdisc;
    }

    public int getStudisc() {
        return studisc;
    }

    public void setStudisc(int studisc) {
        this.studisc = studisc;
    }

    public int getMemdisc() {
        return memdisc;
    }

    public void setMemdisc(int memdisc) {
        this.memdisc = memdisc;
    }
}

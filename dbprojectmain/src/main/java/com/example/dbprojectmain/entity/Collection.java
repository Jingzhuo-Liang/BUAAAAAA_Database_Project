package com.example.dbprojectmain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "collection")
@IdClass(HisAndColUPK.class)
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Collection {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "comid")
    private int comid;

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "userid")
    private int userid;

    private String time;

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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

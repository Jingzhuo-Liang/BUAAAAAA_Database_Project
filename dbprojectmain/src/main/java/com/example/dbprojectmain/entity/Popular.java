package com.example.dbprojectmain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "popular")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Popular {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private int favorable;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFavorable() {
        return favorable;
    }

    public void setFavorable(int favorable) {
        this.favorable = favorable;
    }
}

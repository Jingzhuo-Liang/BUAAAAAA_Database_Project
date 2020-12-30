package com.example.dbprojectmain.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Data
@Table(name = "admins")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ad_id")
    int adId;

    private String adName;
    private String adEmail;
    private String adPwd;
    private int enabled;

    public int getAdId() {
        return adId;
    }

    public void setAdId(int adId) {
        this.adId = adId;
    }

    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName;
    }

    public String getAdEmail() {
        return adEmail;
    }

    public void setAdEmail(String adEmail) {
        this.adEmail = adEmail;
    }

    public String getAdPwd() {
        return adPwd;
    }

    public void setAdPwd(String adPwd) {
        this.adPwd = adPwd;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }
}

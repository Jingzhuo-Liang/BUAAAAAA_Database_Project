package com.example.dbprojectmain.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Data
@Table(name = "Admins")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adId")
    int adId;

    private String adName;
    private String adEmail;
    private String adPwd;
    private int enabled;
}

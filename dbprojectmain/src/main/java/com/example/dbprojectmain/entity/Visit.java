package com.example.dbprojectmain.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Data
@Table(name = "Visits")
@IdClass(VisitUPK.class)
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Visit implements Serializable {
    private LocalDate day;
    @Id
    @Column(name = "userid")
    private int userid;
    @Id
    @Column(name = "time")
    private LocalDateTime time;
}

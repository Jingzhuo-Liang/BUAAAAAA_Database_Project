package com.example.dbprojectmain.entity;

import java.time.LocalDate;
import lombok.Data;
import java.sql.Date;

public interface VvAnswer {
  Date getDay();
  int getCntday();
}
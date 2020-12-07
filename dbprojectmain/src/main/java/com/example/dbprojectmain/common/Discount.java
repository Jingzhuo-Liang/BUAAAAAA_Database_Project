package com.example.dbprojectmain.common;

public class Discount {
    private int limit;
    private int discount;
    private String time;
    private int isMember; // 0 is not

    public Discount(int limit, int discount, String time, int isMember) {
        this.discount = discount;
        this.limit = limit;
        this.time = time;
        this.isMember = isMember;
    }

    public Discount(String s) {
        String[] strs = s.split("-");
        this.limit = Integer.parseInt(strs[0]);
        this.discount = Integer.parseInt(strs[1]);
    }

    public Discount() {}

    public void setIsMember(int isMember) {
        this.isMember = isMember;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getLimit() {
        return limit;
    }

    public int getDiscount() {
        return discount;
    }

    public String getTime() {
        return this.time;
    }

    public int getIsMember() {
        return isMember;
    }
}

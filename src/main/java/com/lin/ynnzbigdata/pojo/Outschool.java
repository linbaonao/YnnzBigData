package com.lin.ynnzbigdata.pojo;

import java.sql.Timestamp;

public class Outschool {
    private Integer id;

    private String stuId;

    private String reason;

    private String place;

    private Timestamp outdate;

    private Timestamp returndate;

    private int number;

    private String plac;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId == null ? null : stuId.trim();
    }


    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place == null ? null : place.trim();
    }

    public Timestamp getOutdate() {
        return outdate;
    }

    public void setOutdate(Timestamp outdate) {
        this.outdate = outdate;
    }

    public Timestamp getReturndate() {
        return returndate;
    }

    public void setReturndate(Timestamp returndate) {
        this.returndate = returndate;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPlac() {
        return plac;
    }

    public void setPlac(String plac) {
        this.plac = plac;
    }

    @Override
    public String toString() {
        return "Outschool{" +
                "id=" + id +
                ", stuId='" + stuId + '\'' +
                ", reason='" + reason + '\'' +
                ", place='" + place + '\'' +
                ", outdate=" + outdate +
                ", returndate=" + returndate +
                ", number=" + number +
                ", plac='" + plac + '\'' +
                '}';
    }
}
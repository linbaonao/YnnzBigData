package com.lin.ynnzbigdata.pojo;

import java.sql.Timestamp;

public class Students {
    private Integer id;

    private String stuId;

    private String stuName;

    private String sex;

    private String status;

    private Integer classId;

    private Classes classes;

    private Outschool outschool;
    // 多表链接的字段信息
    private Timestamp returndate;
    private Timestamp outdate;
    private String teacher;
    private String class_name;
    private String reason;
    private String place;

    @Override
    public String toString() {
        return "Students{" +
                "id=" + id +
                ", stuId='" + stuId + '\'' +
                ", stuName='" + stuName + '\'' +
                ", sex='" + sex + '\'' +
                ", status='" + status + '\'' +
                ", classId=" + classId +
                ", classes=" + classes +
                ", outschool=" + outschool +
                ", returndate=" + returndate +
                ", outdate=" + outdate +
                ", teacher='" + teacher + '\'' +
                ", class_name='" + class_name + '\'' +
                ", reason='" + reason + '\'' +
                ", place='" + place + '\'' +
                ", ot=" + ot +
                ", ret=" + ret +
                ", late=" + late +
                '}';
    }

    public Timestamp getOutdate() {
        return outdate;
    }

    public void setOutdate(Timestamp outdate) {
        this.outdate = outdate;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Timestamp getReturndate() {
        return returndate;
    }

    public void setReturndate(Timestamp returndate) {
        this.returndate = returndate;
    }

    private int ot;

    private int ret;

    private int late;

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

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName == null ? null : stuName.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public Outschool getOutschool() {
        return outschool;
    }

    public void setOutschool(Outschool outschool) {
        this.outschool = outschool;
    }

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public int getOt() {
        return ot;
    }

    public void setOt(int ot) {
        this.ot = ot;
    }

    public int getLate() {
        return late;
    }

    public void setLate(int late) {
        this.late = late;
    }

}
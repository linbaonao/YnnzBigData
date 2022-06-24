package com.lin.ynnzbigdata.pojo;

public class Classes {
    private Integer classId;

    private String className;

    private String teacher;
    private Integer studenNum;

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public Integer getStudenNum() {
        return studenNum;
    }

    public void setStudenNum(Integer studenNum) {
        this.studenNum = studenNum;
    }

    @Override
    public String toString() {
        return "Classes{" +
                "classId=" + classId +
                ", className='" + className + '\'' +
                ", teacher='" + teacher + '\'' +
                ", studenNum=" + studenNum +
                '}';
    }
}
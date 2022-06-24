package com.lin.ynnzbigdata.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author: linbaobao
 * @Date: 2022/5/5
 * @explain: 学院人数实体类
 */
@Getter
@Setter
@ToString

public class College {
    private int id;
    private String college;
    private String students;
    private int returnschool;
}

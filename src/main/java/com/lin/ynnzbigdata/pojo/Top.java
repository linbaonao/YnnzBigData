package com.lin.ynnzbigdata.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

/**
 * @Author: linbaobao
 * @Date: 2022/5/21
 * @explain: top10省份实体类
 */
@Getter
@Setter
@ToString

public class Top {
    private String province;   // top10的省份
    private int number;  // 数量
    private Date date; // 日期
}

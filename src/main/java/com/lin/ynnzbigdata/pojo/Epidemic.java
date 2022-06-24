package com.lin.ynnzbigdata.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

/**
 * @Author: linbaobao
 * @Date: 2022/4/28
 * @explain: 疫情信息实体类
 */

//用lombok注解 生产get、set，重写tostring方法
@Getter
@Setter
@ToString

public class Epidemic {
    private int id;
    private String mainland;  // 全国新增本土
    private String overseas;  // 新增境外
    private String yunnan;  // 云南
    private String ymain;   // 云南新增本土
    private String yovers;  // 新增境外
    private Date date; // 日期
}

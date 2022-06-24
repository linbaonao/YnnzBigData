package com.lin.ynnzbigdata.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author: linbaobao
 * @Date: 2022/4/28
 * @explain: 用户实体类
 */
//用lombok注解 生产get、set，重写tostring方法
@Getter
@Setter
@ToString

public class User {
    private int id;
    private String name;
    private String password;
    private String token;
}

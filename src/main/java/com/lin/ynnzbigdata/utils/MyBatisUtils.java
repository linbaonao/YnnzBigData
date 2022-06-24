package com.lin.ynnzbigdata.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: linbaobao
 * @Date: 2022/4/28
 * @explain: mybatis连接
 */

/*
 * 编写加载核心配置文件，获取sqlsesion对象的方法
 * */
public class MyBatisUtils {
    public static SqlSession getSqlSession() throws IOException {
        String resource = "resource/config/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory.openSession();
    }
}

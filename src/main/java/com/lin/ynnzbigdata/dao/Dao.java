package com.lin.ynnzbigdata.dao;

import com.lin.ynnzbigdata.dao.Mapper.Mappers;
import com.lin.ynnzbigdata.pojo.*;
import com.lin.ynnzbigdata.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: linbaobao
 * @Date: 2022/4/28
 * @explain: 链接mybatis查询的Dao类
 */

public class Dao {
    //登录方法
    public static List<User> Login(String name, String password) throws IOException {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        Mappers Mapper = sqlSession.getMapper(Mappers.class);
        List<User> userList = Mapper.SelectorUser(name, password);
        return userList;

    }

    // 查询全国疫情信息
    public static List<Epidemic> Query() throws IOException {
        List<Epidemic> epidemicList = new ArrayList<Epidemic>();
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        Mappers Mapper = sqlSession.getMapper(Mappers.class);
        epidemicList = Mapper.SelectEpidemic();
        return epidemicList;
    }

    // 查询云南疫情信息
    public static List<Epidemic> queryYn(Timestamp date) throws IOException {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        Mappers Mapper = sqlSession.getMapper(Mappers.class);
        List<Epidemic> epidemicList = Mapper.SelectYn(date);
        return epidemicList;
    }

    // 查询学生家庭地址信息
    public static List<Distribute> Student() throws IOException {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        Mappers Mapper = sqlSession.getMapper(Mappers.class);
        List<Distribute> Slist = Mapper.SelectStudents();
        return Slist;
    }

    // 查询学院信息
    public static List<College> Colleges() throws IOException {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        Mappers Mapper = sqlSession.getMapper(Mappers.class);
        List<College> Clist = Mapper.SelectCollege();
        return Clist;
    }

    // top10省份查询
    public static List<Top> getTop(Date date) throws IOException {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        Mappers Mapper = sqlSession.getMapper(Mappers.class);
        List<Top> topList = Mapper.SelectProvince(date);
        return topList;
    }

    // 多表查询累计学生信息
    public static List<Students> getStu(Timestamp date, Timestamp date1) throws IOException {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        Mappers Mapper = sqlSession.getMapper(Mappers.class);
        List<Students> stlist = Mapper.SelectStuAndcliAndout(date, date1);
        return stlist;
    }

    // 查询班级学生信息
    public static List<Classes> getClasses() throws IOException {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        Mappers Mapper = sqlSession.getMapper(Mappers.class);
        List<Classes> ctlist = Mapper.SelectClasses();
        return ctlist;
    }

    // 多表查询当日学生信息
    public static List<Students> getDay(Timestamp date, Timestamp date1, Timestamp date2) throws IOException {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        Mappers Mapper = sqlSession.getMapper(Mappers.class);
        List<Students> stlist = Mapper.SelectDay(date, date1, date2);
        return stlist;
    }

    /**实时查询离校出校学生信息*/
    public static List<Students> getRealtime(Timestamp date, Timestamp date1) throws IOException {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        Mappers Mapper = sqlSession.getMapper(Mappers.class);
        List<Students> stlist = Mapper.SelectRealtime(date, date1);
        return stlist;
    }

    /**实时查询学生出校分布*/
     public static List<Outschool> getOut(Timestamp date, Timestamp date1) throws IOException {
         SqlSession sqlSession = MyBatisUtils.getSqlSession();
         Mappers Mapper = sqlSession.getMapper(Mappers.class);
         List<Outschool> stlist = Mapper.SelectPlace(date, date1);
         return stlist;
     }

    /**实时查询学生出校分布*/
    public static List<Students> getLate(Timestamp date, Timestamp date2) throws IOException {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        Mappers Mapper = sqlSession.getMapper(Mappers.class);
        List<Students> stlist = Mapper.SelectLate(date, date2);
        return stlist;
    }
    /**导出EXCEL*/
    public static List<Students> getExcel(Timestamp date, Timestamp date1) throws IOException {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        Mappers Mapper = sqlSession.getMapper(Mappers.class);
        List<Students> stlist = Mapper.SelectExcel(date, date1);
        return stlist;
    }
}

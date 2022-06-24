package com.lin.ynnzbigdata.dao.Mapper;

import com.lin.ynnzbigdata.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * @Author: linbaobao
 * @Date: 2022/4/28
 * @explain: 与UserMapper映射文件
 */

//与UserMapper.xml相关联
@Mapper
@Repository
public interface Mappers {
    //查询所有用户
    List<User> SelectorUser(@Param("name") String name, @Param("password") String password);

    // 查询疫情信息
    List<Epidemic> SelectEpidemic();

    // 云南疫情信息
    List<Epidemic> SelectYn(@Param("date") Timestamp date);

    // 查询学生家庭地址信息
    List<Distribute> SelectStudents();

    // 查询各学院学生人数
    List<College> SelectCollege();

    // top省份信息
    List<Top> SelectProvince(@Param("date") Date date);

    // 多表查询累计学生信息
    List<Students> SelectStuAndcliAndout(@Param("date") Timestamp date, @Param("date1") Timestamp date1);

    // 查询各学院学生人数
    List<Classes> SelectClasses();

    // 查询当日出校和销假的信息
    List<Students> SelectDay(@Param("date") Timestamp date, @Param("date1") Timestamp date1, @Param("date2") Timestamp date2);

    /**实时查询离校返校人数*/
    List<Students> SelectRealtime(@Param("date") Timestamp date, @Param("date1") Timestamp date1);

    /**实时查询出校返校信息*/
    List<Outschool> SelectPlace(@Param("date") Timestamp date, @Param("date1") Timestamp date1);

    /**查询晚归信息*/
    List<Students> SelectLate(@Param("date") Timestamp date, @Param("date2") Timestamp date2);

    /**导出excel*/
    List<Students> SelectExcel(@Param("date") Timestamp date, @Param("date1") Timestamp date1);
}


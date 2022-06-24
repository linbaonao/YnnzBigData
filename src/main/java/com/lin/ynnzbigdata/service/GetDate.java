package com.lin.ynnzbigdata.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Author: linbaobao
 * @Date: 2022/6/7
 * @explain:  封装获取系统时间的方法
 */
public class GetDate {

    public static List<Timestamp> GetDate(){
        // 获取当日0点时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(new Date());
//        calendar.set(Calendar.HOUR_OF_DAY, 0);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
        //今日凌晨
//        String day = format.format(calendar.getTime());
        String day = "2020-06-13 00:00:00";
        java.sql.Timestamp date = java.sql.Timestamp.valueOf(day);
        // 获取当前系统时间
        calendar.setTime(new Date());
        calendar.set(Calendar.MONTH,5);
        calendar.set(Calendar.DATE,13);
        String day1 = format.format(calendar.getTime());
        java.sql.Timestamp date1 = java.sql.Timestamp.valueOf(day1);
        // 获取晚归时间
//        calendar.setTime(new Date());
//        calendar.set(Calendar.HOUR_OF_DAY, 22);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
//        String day2 = format.format(calendar.getTime());
        String day2 = "2020-06-13 22:00:00";
        java.sql.Timestamp date2 = java.sql.Timestamp.valueOf(day2);
        // 创建集合打包三个时间
        List<Timestamp> tlist = new ArrayList<>();
        tlist.add(date);
        tlist.add(date1);
        tlist.add(date2);
        return tlist;
    }
}

package com.lin.ynnzbigdata.service;

import com.google.gson.Gson;
import com.lin.ynnzbigdata.dao.Dao;
import com.lin.ynnzbigdata.pojo.*;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Author: linbaobao
 * @Date: 2022/4/29
 * @explain: controller的服务类
 */
@Service
public class Services {
    static Gson gson = new Gson();

    /**
     * 查询云南疫情信息，近五天
     */
    public static List<List> getynData() throws IOException {
        // 获取系统时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, 5);
        calendar.set(Calendar.DATE, 8);
        String day2 = format.format(calendar.getTime());
        Timestamp date1 = Timestamp.valueOf(day2);
        List<Epidemic> List = Dao.queryYn(date1);
        // 定义集合防止所需信息
        // 云南新增本土
        List<String> ymain = new ArrayList<String>();
        // 境外
        List<String> yovers = new ArrayList<String>();
        // 日期
        List<String> date = new ArrayList<String>();
        for (int i = 0; i < List.size(); i++) {
            ymain.add(List.get(i).getYmain());
            yovers.add(List.get(i).getYovers());
            date.add(String.valueOf(List.get(i).getDate()));
        }
        // 定义结果集传递给前端
        List<List> result = new ArrayList<>();
        result.add(ymain);
        result.add(yovers);
        result.add(date);

        return result;
    }


    /**
     * 获取当前时间top的省份信息
     */
    public static String getTop() throws IOException {
        // 获取系统时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        //做实验的天数
        c.set(Calendar.DATE, 13);
        Date d = c.getTime();
        String day = format.format(d);
        java.sql.Date date1 = java.sql.Date.valueOf(day);
        List<Top> tist = Dao.getTop(date1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tist.size(); i++) {
            sb.append("[" + "\"" + tist.get(i).getDate() + "\"" + "," + "\"" + tist.get(i).getProvince() + "\"" + "," + tist.get(i).getNumber() + "]" + ",");
        }
        // 去除最后一项
        sb.deleteCharAt(sb.length() - 1);
        String result = "[" + sb + "]";
        return result;
    }

    /**
     * 获取累计各班人数和请假信息
     */
    public static String getStudents() throws IOException {
        /* 班级信息*/
        List<Classes> clist = Dao.getClasses();
        List<Timestamp> tlist = GetDate.GetDate();
        Timestamp date = tlist.get(0);
        Timestamp date1 = tlist.get(1);
        Timestamp date2 = tlist.get(2);
        // 请假信息
        List<Students> slist = Dao.getStu(date, date1);
        if (slist.size() > 0) {
            // 因21数据没有造，需要单独拼接20级班级信息和请假情况
            // 获取20级班级数据
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 21; i++) {
                sb.append("[" + "\"" + clist.get(i).getClassName() + "\"" + "," + "\"" + clist.get(i).getTeacher() + "\"" + "," + clist.get(i).getStudenNum() + "," + slist.get(i).getOt() + "," + slist.get(i).getRet() + "]" + ",");
            }
            // 获取21班级信息 请假
            List<String> list1 = new ArrayList<String>();
            for (int i = 0; i < clist.size(); i++) {
                list1.add("[" + "\"" + clist.get(i).getClassName() + "\"" + "," + "\"" + clist.get(i).getTeacher() + "\"" + "," + clist.get(i).getStudenNum() + "," + "0" + "," + "0" + "]" + ",");
            }
            // 去除20级的数据
            for (int i = 0; i < 21; i++) {
                list1.remove(0);
            }
            // 21级学生请假信息
            StringBuilder sb1 = new StringBuilder();
            for (int i = 0; i < list1.size(); i++) {
                sb1.append(list1.get(i));
            }
            // 总数据
            StringBuilder sb2 = new StringBuilder();
            sb2.append("" + sb + sb1);
            // 去除最后一项
            sb2.deleteCharAt(sb2.length() - 1);
            // 定义结果集
            String result = "[" + sb2 + "]";
            return result;
        }
        return null;
    }

    /**
     * 获取管理信息
     */
    public static String getDay() throws IOException {
        // 获取学生人数
        List<Classes> clist = Dao.getClasses();
        int students = 0;
        for (int i = 0; i < clist.size(); i++) {
            students += clist.get(i).getStudenNum();
        }
        // 获取累计请假销假
        List<Timestamp> tlist = GetDate.GetDate();
        Timestamp date = tlist.get(0);
        Timestamp date1 = tlist.get(1);
        Timestamp date2 = tlist.get(2);
        List<Students> slist = Dao.getDay(date, date1, date2);
        // out出校人数，ret返校人数
        int out = 0, ret = 0;
        for (int i = 0; i < slist.size(); i++) {
            out += slist.get(i).getOt();
            ret += slist.get(i).getRet();
        }
        // 获取累计请假学生人数
        List<Students> addList = Dao.getStu(date, date1);
        // out出校人数，ret返校人数
        int addOut = 0, addRet = 0;
        for (int i = 0; i < slist.size(); i++) {
            addOut += addList.get(i).getOt();
            addRet += addList.get(i).getRet();
        }
        // 获取今日学生请假信息
        String result = "[" + "[" + students + "]" + "," + "[" + addOut + "]" + "," + "[" + addRet + "]" + "," + "[" + out + "]" + "," + "[" + ret + "]" + "]";
        return result;
    }

    /**
     * 获取当日信息
     */
    public static String getToDay() throws IOException {
        List<Timestamp> tlist = GetDate.GetDate();
        Timestamp date = tlist.get(0);
        Timestamp date1 = tlist.get(1);
        Timestamp date2 = tlist.get(2);
        List<Students> slist = Dao.getDay(date, date1, date2);
        // out出校人数，ret返校人数
        int out = 0, ret = 0;
        for (int i = 0; i < slist.size(); i++) {
            out += slist.get(i).getOt();
            ret += slist.get(i).getRet();
        }
        // 获取今日学生请假信息
        String result = "[" + out + "]" + "," + "[" + ret + "]" + "]";
        return result;
    }

    /**
     * 实时查询离校返校信息
     */
    public static String getReaalTime() throws IOException {
        List<Timestamp> tlist = GetDate.GetDate();
        Timestamp date = tlist.get(0);
        Timestamp date1 = tlist.get(1);
        List<Students> slist = Dao.getRealtime(date, date1);
        // out出校人数，ret返校人数
        int out = 0, ret = 0;
        for (int i = 0; i < slist.size(); i++) {
            out = slist.get(i).getOt();
            ret = slist.get(i).getRet();
        }
        String result = "[" + "[" + out + "]" + "," + "[" + ret + "]" + "]";
        return result;
    }


    /**
     * 导出excel
     */
    public static void dowlod(HttpServletResponse response) throws IOException {
        //查询所有员工信息
        List<Timestamp> tlist = GetDate.GetDate();
        Timestamp date = tlist.get(0);
        Timestamp date1 = tlist.get(1);
        List<Students> slist = Dao.getExcel(date, date1);
        //创建一个工作簿
        @SuppressWarnings("resource")
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("登录日志");
        //创建单元格，并设置值表头 设置表头居中
        CellStyle titleStyle = workbook.createCellStyle();
        //水平居中
        titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //垂直居中
        titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        //字体样式
        XSSFFont font = (XSSFFont) workbook.createFont();
        font.setFontName("仿宋_GB2312");
        //粗体显示
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        font.setFontHeightInPoints((short) 12);
        titleStyle.setFont(font);

        //定义标题
        String[] titles = {"编号", "姓名", "班级", "班主任", "事由", "地点", "出校时间", "贵校时间"};
        //行
        Row row = sheet.createRow(0);
        //写入标题行
        for (int i = 0; i < titles.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(titles[i]);
            //标题行设置宽高 256为单个字符所占的宽度 1.2d表示比实际宽度大20% 12*256表示最低宽度为12个字符
            sheet.setColumnWidth(i, (int) (titles[i].getBytes().length * 1.2d * 256 > 12 * 256 ? titles[i].getBytes().length * 1.2d * 256 : 12 * 256));
            cell.setCellStyle(titleStyle);
        }
        //创建单元格，并设置值表头 设置表头居中
        CellStyle cellStyle = workbook.createCellStyle();
        //水平居中
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //垂直居中
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        //自动换行
        cellStyle.setWrapText(true);
        //根据标题查询行数
        for (int i = 0; i < slist.size(); i++) {
            //创建第i+1行
            row = sheet.createRow(i + 1);
            //得到第一个字段的值
            Students mid = slist.get(i);
            //创建第i个列
            Cell empnoCell = row.createCell(0);
            //写入第i+1行第i列  写入第一个字符
            empnoCell.setCellValue(mid.getId());
            //写第二个字符
            Cell jobCell = row.createCell(1);
            jobCell.setCellValue(mid.getStuName());
            Cell hiredateCell = row.createCell(2);
            hiredateCell.setCellValue(mid.getClass_name());
            Cell salCell = row.createCell(3);
            salCell.setCellValue(mid.getTeacher());
            Cell rCell = row.createCell(4);
            rCell.setCellValue(mid.getReason());
            Cell pCell = row.createCell(5);
            pCell.setCellValue(mid.getPlace());
            Cell oCell = row.createCell(6);
            oCell.setCellValue(mid.getOutdate());
            Cell reCell = row.createCell(7);
            reCell.setCellValue(mid.getReturndate());
            //设置居中
            row.getCell(0).setCellStyle(cellStyle);
            row.getCell(1).setCellStyle(cellStyle);
            row.getCell(2).setCellStyle(cellStyle);
            row.getCell(3).setCellStyle(cellStyle);
            row.getCell(4).setCellStyle(cellStyle);
            row.getCell(5).setCellStyle(cellStyle);
            row.getCell(6).setCellStyle(cellStyle);
            row.getCell(7).setCellStyle(cellStyle);
            //创建文件名称
            String fileName = URLEncoder.encode("出校学生返校详情表.xlsx", "UTF-8");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            response.setHeader("filename", fileName);
            //写入文件
            workbook.write(response.getOutputStream());
        }
    }

    /**
     * 实时查询出校分布
     */
    public static String getOut() throws IOException {

        List<Timestamp> tlist = GetDate.GetDate();
        Timestamp date = tlist.get(0);
        Timestamp date1 = tlist.get(1);

        StringBuilder sb = new StringBuilder();
        List<Outschool> olist = Dao.getOut(date, date1);
        for (int i = 0; i < olist.size(); i++) {
            sb.append("{" + "\"name\":" + "\"" + olist.get(i).getPlace() + "\"" + "," + "\"value\":" + olist.get(i).getPlac() + "},");
        }
        String result = null;
        // 去除最后一项
        sb.deleteCharAt(sb.length() - 1);
        result = "[" + sb + "]";
        return result;
    }

    /**
     * 获取晚归信息
     */
    public static String getLate() throws IOException {
        List<Timestamp> tlist = GetDate.GetDate();
        Timestamp date = tlist.get(0);
        Timestamp date2 = tlist.get(2);

        List<Students> list = Dao.getLate(date, date2);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append("[" + "\"" + list.get(i).getReturndate() + "\"" + "," + "\"" + list.get(i).getStuName() + "\"" + "," + "\"" + list.get(i).getClasses().getClassName() + "\"" + "]" + ",");
        }
        String result = null;
        // 去除最后一项
        sb.deleteCharAt(sb.length() - 1);
        result = "[" + sb + "]";
        return result;
    }
}
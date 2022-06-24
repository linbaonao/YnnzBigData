package com.lin.ynnzbigdata.controller;

import com.google.gson.Gson;
import com.lin.ynnzbigdata.dao.Dao;
import com.lin.ynnzbigdata.pojo.*;
import com.lin.ynnzbigdata.service.GetDate;
import com.lin.ynnzbigdata.service.JWToken;
import com.lin.ynnzbigdata.service.Services;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: linbaobao
 * @Date: 2022/4/28
 * @explain: 后端数据访问请求
 */

/**
 * @Controller
 */
@RestController
public class controller extends GeneralController {
    /**
     * 全局调用gson对象
     */
    Gson gson = new Gson();

    /**
     * 登录操作
     */
    @RequestMapping("/login")
    @ResponseBody
    public User login(@RequestBody User user) throws IOException {
        //获取用户输入的值
        String name = user.getName();
        String password = user.getPassword();
        //调用登录方法
        List<User> List = Dao.Login(name, password);
        if (List.size() > 0) {
            //添加token
            user.setToken(JWToken.createToken());
            return user;
        }
        return null;
    }

    //校验token
    @GetMapping("/checkToken")
//接收前端请求过来的header中的token，然后去jwtoken校验方法里校验这个token
    public Boolean checkToken(HttpServletRequest request) {
        String token = request.getHeader("token");
//        System.out.println(token);
        return JWToken.checkToken(token);
    }

    /**
     * 查询全国新增疫情信息
     */
    @RequestMapping("/epi")
    public List<List> query() throws IOException {
            List<Epidemic> List = Dao.Query();
            // 定义集合防止所需信息
            // 全国新增本土
            List<String> Mainland = new ArrayList<String>();
            // 境外
            List<String> Overseas = new ArrayList<String>();
            // 日期
            List<String> Date = new ArrayList<String>();
            for (int i = 0; i < List.size(); i++) {
                Mainland.add(List.get(i).getMainland());
                Overseas.add(List.get(i).getOverseas());
                Date.add(String.valueOf(List.get(i).getDate()));
            }
            // 定义结果集传递给前端
            List<List> result = new ArrayList<>();
            result.add(Mainland);
            result.add(Overseas);
            result.add(Date);
            return result;
    }

    /**
     * 查询学生家庭地址
     */
    @RequestMapping("/stu")
    public String students() throws IOException {
        // 调用查询方法
        List<Distribute> slist = Dao.Student();
        // 创建String返回值给前端
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < slist.size(); i++) {
            sb.append("{" + "\"name\":" + "\"" + slist.get(i).getCity() + "\"" + "," + "\"value\":" + slist.get(i).getStudents() + "},");
        }
        String result = null;
        // 去除最后一项
        sb.deleteCharAt(sb.length() - 1);
        result = "[" + sb + "]";
        return result;
    }

    /**
     * 查询各学院学生人数
     */
    @RequestMapping("/col")
    public String college() throws IOException {
        // 调用查询方法
        List<College> clist;
        clist = Dao.Colleges();
        // 创建String返回值给前端
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < clist.size(); i++) {
            sb.append("{" + "\"name\":" + "\"" + clist.get(i).getCollege() + "\"" + "," + "\"value\":" + clist.get(i).getStudents() + "},");
        }
        // 去除最后一项
        sb.deleteCharAt(sb.length() - 1);
        String result = "[" + sb + "]";
        return result;
    }

    /**
     * 学院返校信息
     */
    @RequestMapping("/ret")
    public String retuns() throws IOException {
        // 调用查询方法
        List<College> rlist;
        rlist = Dao.Colleges();
        // 创建String返回值给前端
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rlist.size(); i++) {
            sb.append("{" + "\"name\":" + "\"" + rlist.get(i).getCollege() + "\"" + "," + "\"value\":" + rlist.get(i).getReturnschool() + "},");
        }
        // 去除最后一项
        sb.deleteCharAt(sb.length() - 1);
        String result = "[" + sb + "]";
        return result;
    }

    /**
     * 云南疫情信息
     */
    @RequestMapping("/yn")
    public List<List> getYnDate() throws IOException {
        // 定义List集合去接查询集合
        List<List> ylist = Services.getynData(); // 调用services中的查询云南疫情方法
        return ylist;
    }

    /**
     * 境外输入top10省份数据
     */
    @RequestMapping("/top")
    public String getTopprovince() throws IOException {
        String tlist = Services.getTop();
        return tlist;
    }

    /**
     * 查询累计请假销假信息
     */
    @RequestMapping("st")
    public String getStudents() throws IOException {
        String result = Services.getStudents();
        return result;
    }

    /**
     * 查询当日学生请假情况
     */
    @RequestMapping("day")
    public String getDay() throws IOException {
        String result = Services.getDay();
        return result;
    }

    /**
     * 当日学生返校离校对比
     */
    @RequestMapping("/today")
    public String getToDay() throws IOException {
        String result = Services.getToDay();
        return result;
    }

    /**
     * 实时查询学生离校返校信息
     */
    @RequestMapping("real")
    public String getRealtime() throws IOException {
        String result = Services.getReaalTime();
        return result;
    }

    /**
     * 导出excel格式
     */
    @RequestMapping("/download")
    public void download(HttpServletResponse response) throws Exception {
        //查询所有员工信息
        List<Timestamp> tlist = GetDate.GetDate();
        Timestamp date = tlist.get(0);
        Timestamp date1 = tlist.get(1);
        List<Students> slist = Dao.getExcel(date, date1);
        //创建一个工作簿
        @SuppressWarnings("resource")
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("出校学生返校");
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
        //主标题
        Row row = sheet.createRow(0);
        // 创建单元格
        row.createCell(0).setCellValue("出校学生返校详情");
        row.getCell(0).setCellStyle(titleStyle);
        row.createCell(1).setCellValue("");
        row.createCell(2).setCellValue("");
        row.createCell(3).setCellValue("");
        row.createCell(4).setCellValue("");
        row.createCell(5).setCellValue("");
        row.createCell(6).setCellValue("");
        row.createCell(7).setCellValue("");
        // 合并单元格,合并后的内容取决于合并区域的左上角单元格的值
        CellRangeAddress region = new CellRangeAddress(0, 0, 0, 7);
        sheet.addMergedRegion(region);
        //定义标题
        String[] titles = {"编号", "姓名", "班级", "班主任", "事由", "地点", "出校时间", "归校时间"};
        //行
        row = sheet.createRow(1);
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
        //设置时间类型
        CellStyle cellStyles = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();
        short dateFormat = createHelper.createDataFormat().getFormat("yyyy/mm/dd hh:mm:ss");
        //水平居中
        cellStyles.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //垂直居中
        cellStyles.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        //自动换行
        cellStyles.setWrapText(true);
        cellStyles.setDataFormat(dateFormat);
        //根据标题查询行数
        for (int i = 1; i < slist.size(); i++) {
            //创建第i+1行
            row = sheet.createRow(i + 1);
            //得到第一个字段的值
            Students mid = slist.get(i - 1);
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
            //设置列宽
            sheet.setColumnWidth(6, 20 * 300);
            oCell.setCellValue(mid.getOutdate());
            Cell reCell = row.createCell(7);
            //设置列宽
            sheet.setColumnWidth(7, 20 * 300);
            reCell.setCellValue(mid.getReturndate());
            //设置居中
            row.getCell(0).setCellStyle(cellStyle);
            row.getCell(1).setCellStyle(cellStyle);
            row.getCell(2).setCellStyle(cellStyle);
            row.getCell(3).setCellStyle(cellStyle);
            row.getCell(4).setCellStyle(cellStyle);
            row.getCell(5).setCellStyle(cellStyle);
            row.getCell(6).setCellStyle(cellStyles);
            row.getCell(7).setCellStyle(cellStyles);
        }
        //创建文件名称
        String fileName = URLEncoder.encode("出校学生返校详情表.xlsx", "UTF-8");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.setHeader("filename", fileName);
        //写入文件
        workbook.write(response.getOutputStream());
    }

    /**
     * 实时查询学生分布
     */
    @RequestMapping("/getout")
    public String getOut() throws IOException {
        String result = Services.getOut();
        return result;
    }

    /**
     * 获取晚归信息
     */
    @RequestMapping("/getlate")
    public String getLate() throws IOException {
        String result = Services.getLate();
        return result;
    }
}

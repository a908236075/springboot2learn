package com.xiaobo.springboot2.util;

import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.junit.jupiter.api.Test;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

class ExcelPOTest {

    @Test
    public void dateTest() throws ParseException {
        String expireTime = "2022-12-1";
        System.out.println(expireTime.length());
        String replaceDate = expireTime.replace("/", "-");
        if (!replaceDate.contains(":")){
            replaceDate+=" 00:00";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date expireTimeDate = simpleDateFormat.parse(replaceDate);
        System.out.println(expireTimeDate);
        String password = DecryptUtils.encrypt("123456");
    }

    @Test
    public void setTest(){
        Set set=new HashSet<String>();
        set.add("zhangsan");
        set.add("lisi");
        set.add("zhangsan");
        Object[] userArr = set.toArray();
        for (Object userName : userArr) {
            System.out.println(userName);
        }
    }
    @Test
    public void excelCreateTest() throws UnsupportedEncodingException {
        HttpServletResponse response = null;
        HSSFWorkbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("用户列表");
        Row row = sheet.createRow(0);
        String[] fields = "用户名;有效期;状态;ip登录限制;角色;类型;用户姓名;职位;部门;Email;电话;传真".split(";");
        for (int i = 0; i < fields.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(fields[i]);
        }
        CellRangeAddressList regions = new CellRangeAddressList(1, 65535, 4, 4);
        DVConstraint constraint = DVConstraint.createExplicitListConstraint(new String[]{"帐户管理员", "审计管理员", "操作管理员"});
        HSSFDataValidation dataValidate = new HSSFDataValidation(regions, constraint);
        sheet.addValidationData(dataValidate);
        String fileName = "用户模板" + UUID.randomUUID();
        String codeFileName = new String(fileName.getBytes(), "iso-8859-1");
        response.setHeader("Content-Disposition", "attachment;filename=" + codeFileName + ".xls");
//				response.setContentType("application/x-xls");
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            workbook.write(outputStream);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outputStream.close();
             workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }





    }



}
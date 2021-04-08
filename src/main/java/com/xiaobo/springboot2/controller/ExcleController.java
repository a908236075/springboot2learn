package com.xiaobo.springboot2.controller;

import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

@Controller
public class ExcleController {

    @ResponseBody
    @GetMapping("/downLoadUserExcel")
    public void success(HttpServletResponse response) throws IOException {
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
        String codeFileName = null;
        try {
            codeFileName = new String(fileName.getBytes(), "iso-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
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
        }finally {
            outputStream.close();
            workbook.close();
        }
    }

}

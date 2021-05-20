package com.xiaobo.springboot2.util;

import cn.hutool.poi.excel.ExcelReader;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.List;
import java.util.Map;

public class ExcelHu {
    /*public static void main(String[] args) throws IOException {
//        String msg="测试测试测试测试";
//        ByteArrayInputStream stringStream = new ByteArrayInputStream(msg.getBytes());
//        FileInputStream fileInputStream = new FileInputStream(new File("classpath:用户列表.xls"));
        ClassPathResource classPathResource = new ClassPathResource("static/userInfo.xls");
        InputStream stream = classPathResource.getInputStream();
        ExcelReader excelReader = new ExcelReader(stream, 0).setIgnoreEmptyRow(true);
        List<Map<String, Object>> maps = excelReader.readAll();
        for (Map<String, Object> map : maps) {
            for (String key : map.keySet()) {
                System.out.print(key);
                System.out.println(map.get(key));
            }
        }


    }*/

}

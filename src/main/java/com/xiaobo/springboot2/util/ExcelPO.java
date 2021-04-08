package com.xiaobo.springboot2.util;

import com.xiaobo.springboot2.entity.BaseInfo;
import com.xiaobo.springboot2.entity.UserForm;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelPO {

    public static void main(String[] args) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("static/userInfo.xls");
        InputStream fis = classPathResource.getInputStream();
        List<UserForm> userFormList = new ArrayList<>();
        Workbook workbook = new HSSFWorkbook(fis);
        fis.close();

        Sheet sheet = workbook.getSheetAt(0);
        // 获取行
        Iterator<Row> rows = sheet.rowIterator();
        Row row;
        Cell cell;
        while (rows.hasNext()) {
            row = rows.next();
            if (row.getRowNum() == 0) {
                continue;
            }
            // 获取单元格
            if (row.getLastCellNum() == 13) {
//                Iterator<Cell> cells = row.cellIterator();
                List<String> fieldValuesList = new ArrayList<>();
                // 不能忽略空格
                for (int i = 0; i < row.getLastCellNum(); i++) {
//                    cell = cells.next();
                   cell = row.getCell(i);
                    String cellValue = POIUtil.getCellValue(cell);
                    fieldValuesList.add(cellValue);
                }
                int cellNum = 0;
                String username = fieldValuesList.get(cellNum++);
                UserForm uf = new UserForm();
                if (!StringUtils.isEmpty(username)) {
                    try {
                        BaseInfo baseInfo = new BaseInfo();
                        baseInfo.setName(username);
                        String expireTime = fieldValuesList.get(cellNum++);
                        String replaceDate = expireTime.replace("/", "-");
                        if (!replaceDate.contains(":")){
                            replaceDate+=" 00:00";
                        }
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                        Date expireTimeDate = simpleDateFormat.parse(replaceDate);
                        Timestamp timestamp = new Timestamp(expireTimeDate.getTime());
                        uf.setExpireTime(timestamp);
                        uf.setStatus(Boolean.parseBoolean(fieldValuesList.get(cellNum++)));
                        Set<Object> ipSet = new HashSet<>();
                        ipSet.add(fieldValuesList.get(cellNum++));
                        uf.setAllowIps(ipSet);
                        uf.setRoleName(fieldValuesList.get(cellNum++));
                        uf.setType(fieldValuesList.get(cellNum++));
                        uf.setUserName(fieldValuesList.get(cellNum++));
                        uf.setCaption(fieldValuesList.get(cellNum++));
                        uf.setDepartment(fieldValuesList.get(cellNum++));
                        uf.setEmail(fieldValuesList.get(cellNum++));
                        uf.setPhone(fieldValuesList.get(cellNum++));
                        uf.setFax(fieldValuesList.get(cellNum++));
                        uf.setCreator(fieldValuesList.get(cellNum++));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                userFormList.add(uf);
            }
        }
        for (UserForm userForm : userFormList) {
            System.out.println(userForm);
        }

    }
}

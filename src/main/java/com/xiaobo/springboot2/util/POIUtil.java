package com.xiaobo.springboot2.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;

import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

public class POIUtil {
    /**
     * ��ȡcell�е�ֵ������String����
     * 
     * @param cell
     * @return String���͵�cellֵ
     */
    public static String getCellValue(Cell cell) {
        String cellValue = "";
        if (null != cell) {
            // �������ж����ݵ�����
            switch (cell.getCellType()) {
                case HSSFCell.CELL_TYPE_NUMERIC: // ����
                    if (0 == cell.getCellType()) {// �жϵ�Ԫ��������Ƿ���NUMERIC����
                        if (HSSFDateUtil.isCellDateFormatted(cell)) {// �ж��Ƿ�Ϊ��������
                            Date date = cell.getDateCellValue();
//                      DateFormat formater = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                            DateFormat formater = new SimpleDateFormat("yyyy/MM/dd");
                            cellValue = formater.format(date);
                        } else {
                            // ��Щ���ֹ���ֱ�����ʹ�õ��ǿ�ѧ�������� 2.67458622E8 Ҫ���д���
                            DecimalFormat df = new DecimalFormat("####.####");
                            cellValue = df.format(cell.getNumericCellValue());
                            // cellValue = cell.getNumericCellValue() + "";
                        }
                    }
                    break;
                case HSSFCell.CELL_TYPE_STRING: // �ַ���
                    cellValue = cell.getStringCellValue();
                    break;
                case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                    cellValue = cell.getBooleanCellValue() + "";
                    break;
                case HSSFCell.CELL_TYPE_FORMULA: // ��ʽ
                    try {
                        // �����ʽ���Ϊ�ַ���
                        cellValue = String.valueOf(cell.getStringCellValue());
                    } catch (IllegalStateException e) {
                        if (HSSFDateUtil.isCellDateFormatted(cell)) {// �ж��Ƿ�Ϊ��������
                            Date date = cell.getDateCellValue();
//                      DateFormat formater = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                            DateFormat formater = new SimpleDateFormat("yyyy/MM/dd");
                            cellValue = formater.format(date);
                        } else {
                            FormulaEvaluator evaluator = cell.getSheet().getWorkbook().getCreationHelper()
                                    .createFormulaEvaluator();
                            evaluator.evaluateFormulaCell(cell);
                            // ��Щ���ֹ���ֱ�����ʹ�õ��ǿ�ѧ�������� 2.67458622E8 Ҫ���д���
                            DecimalFormat df = new DecimalFormat("####.####");
                            cellValue = df.format(cell.getNumericCellValue());
//                          cellValue = cell.getNumericCellValue() + "";
                        }
                    }
//              //ֱ�ӻ�ȡ��ʽ
//              cellValue = cell.getCellFormula() + "";
                    break;
                case HSSFCell.CELL_TYPE_BLANK: // ��ֵ
                    cellValue = " ";
                    break;
                case HSSFCell.CELL_TYPE_ERROR: // ����
                    cellValue = "�Ƿ��ַ�";
                    break;
                default:
                    cellValue = "δ֪����";
                    break;
            }
        }
        return cellValue;
    }

    public static String readXls(String path)
    {
        String text="";
        try
        {
            FileInputStream is =  new FileInputStream(path);
            HSSFWorkbook excel=new HSSFWorkbook(is);
            //��ȡ��һ��sheet
            HSSFSheet sheet0=excel.getSheetAt(0);
            for (Iterator rowIterator = sheet0.iterator(); rowIterator.hasNext();)
            {
                HSSFRow row=(HSSFRow) rowIterator.next();
                for (Iterator iterator=row.cellIterator();iterator.hasNext();)
                {
                    HSSFCell cell=(HSSFCell) iterator.next();
                    //���ݵ�Ԫ�ĵ����� ��ȡ��Ӧ�Ľ��
                    if(cell.getCellType()==HSSFCell.CELL_TYPE_STRING) text+=cell.getStringCellValue()+"\t";
                    else if(cell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC) text+=cell.getNumericCellValue()+"\t";
                    else if(cell.getCellType()==HSSFCell.CELL_TYPE_FORMULA) text+=cell.getCellFormula()+"\t";
                }
                text+="\n";
            }
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return text;
    }

   /* public static String readXlsx(String path)
    {
        String text="";
        try
        {
            OPCPackage pkg=OPCPackage.open(path);
            XSSFWorkbook excel=new XSSFWorkbook(pkg);
            //��ȡ��һ��sheet
            XSSFSheet sheet0=excel.getSheetAt(0);
            for (Iterator rowIterator=sheet0.iterator();rowIterator.hasNext();)
            {
                XSSFRow row=(XSSFRow) rowIterator.next();
                for (Iterator iterator=row.cellIterator();iterator.hasNext();)
                {
                    XSSFCell cell=(XSSFCell) iterator.next();
                    //���ݵ�Ԫ�ĵ����� ��ȡ��Ӧ�Ľ��
                    if(cell.getCellType()==XSSFCell.CELL_TYPE_STRING) text+=cell.getStringCellValue()+"\t";
                    else if(cell.getCellType()==XSSFCell.CELL_TYPE_NUMERIC) text+=cell.getNumericCellValue()+"\t";
                    else if(cell.getCellType()==XSSFCell.CELL_TYPE_FORMULA) text+=cell.getCellFormula()+"\t";
                }
                text+="\n";
            }
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return text;
    }*/

}
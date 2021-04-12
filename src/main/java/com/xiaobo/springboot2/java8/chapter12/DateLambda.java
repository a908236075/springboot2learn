package com.xiaobo.springboot2.java8.chapter12;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class DateLambda {

    public static void main(String[] args) {

        LocalDate date = LocalDate.of(2014, 3, 18);
        String isoDate = date.format(DateTimeFormatter.BASIC_ISO_DATE);
        String localIsoDate = date.format(DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(isoDate);
        System.out.println(localIsoDate);
        System.out.println("======================");
        LocalDate parseDate1 = LocalDate.parse("20200101", DateTimeFormatter.BASIC_ISO_DATE);
        LocalDate parseDate2 = LocalDate.parse("2020-01-12", DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(parseDate1);
        System.out.println(parseDate2);
        System.out.println("=======================");
        LocalDateTime localDateTime = date.atTime(13, 12);
        LocalDateTime time2 = LocalDateTime.of(2021, 12, 12, 12, 12, 12);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//        String format = date.format(dateTimeFormatter);
        String time = time2.format(dateTimeFormatter);
        System.out.println(time);
        Period tenDays = Period.between(LocalDate.of(2017, 9, 11),
                LocalDate.of(2017, 9, 21));
        System.out.println("===========");
        System.out.println(tenDays);
        // Zone
        System.out.println(TimeZone.getDefault().getID());


    }


}

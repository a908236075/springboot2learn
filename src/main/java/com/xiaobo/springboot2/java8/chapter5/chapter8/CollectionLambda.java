package com.xiaobo.springboot2.java8.chapter5.chapter8;

import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CollectionLambda {

    public static void main(String[] args) {
//        ArrayList<Integer> numList1 = new ArrayList<>();
//        numList1.add(1);
//        numList1.add(2);
//        numList1.add(3);
//        numList1.add(4);
//        boolean b = numList1.removeIf(x -> x.equals(2));
//        numList1.forEach(System.out::println);
//        replaceAllTest();
        handleMapTest();
    }

    /**
     * 将集合中子单词首字母大写
     */
    public static void replaceAllTest(){
        ArrayList<String> strList = new ArrayList<>();
        strList.add("apple");
        strList.add("orange");
        strList.add("banana");
        strList.add("coco");
        strList.replaceAll(code->Character.toUpperCase(code.charAt(0))+code.substring(1));
        strList.forEach(System.out::println);

    }

    /**
     * 操作Map
     */
    public static void handleMapTest(){
        HashMap<String, String> favMovies = new HashMap<>();
        favMovies.put("Cristina","Matrix");
        favMovies.put("Olivia","James Bond");
        favMovies.put("Raphael","Star Wars");
        favMovies.forEach((key,value)->{
            System.out.println("Key is "+key+" Value is "+value);
        });
        System.out.println("===========");
        // 排序
        favMovies.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(System.out::println);
        System.out.println("===========");
        favMovies.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(System.out::println);








    }





}

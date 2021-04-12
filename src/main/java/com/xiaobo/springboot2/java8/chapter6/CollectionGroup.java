package com.xiaobo.springboot2.java8.chapter6;

import com.xiaobo.springboot2.java8.chapter2.Dish;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

public class CollectionGroup {

    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("seasonal fruit", true, 120, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER));
//        groupByType(menu);
//        partingBy(menu);
        computeIfAbsent();
    }

    static void groupByType(List<Dish> menu) {
        Map<String, List<Dish>> collect = menu.stream().collect(groupingBy(Dish::getType));
        Map<String, Long> countCol = menu.stream().collect(groupingBy(Dish::getType, counting()));
        System.out.println(countCol.entrySet());
      /*  System.out.println(collect.keySet());
        System.out.println("================");
        System.out.println(collect.entrySet());*/
    }
    static void partingBy(List<Dish> menu){
        Map<Boolean, List<Dish>> collect = menu.stream().collect(partitioningBy(Dish::isB));
        System.out.println(collect.get(false));
    }

    static void computeIfAbsent() {
        HashMap<String,String> map1 = new HashMap<>();
        map1.put("1", "Hello");
        map1.put("2", "World");
        String s = map1.computeIfAbsent("3", key -> map1.put("3", "!!!!"));
        System.out.println(s);
        map1.forEach((key,value)-> System.out.println("map key is "+key+" map value is "+value));
        map1.remove("3");
        map1.forEach((key,value)-> System.out.println("map key is "+key+" map value is "+value));


    }


}

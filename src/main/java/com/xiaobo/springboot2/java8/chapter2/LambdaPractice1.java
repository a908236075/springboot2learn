package com.xiaobo.springboot2.java8.chapter2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LambdaPractice1 {

    public static void main(String[] args) {
        List<Apple> inventory = new ArrayList<>();
//        Comparator<Apple> byWeight = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
//        boolean weight=(Apple a1) -> !(a1.getWeight() <= 150);
//        (int x,int y)->{
//            System.out.println("Result:");
//            System.out.println(x+y);
//        };
//        Predicate<>
//        Function<Integer,Integer> f=x->x+1;
//        Integer result = f.apply(10);
//        System.out.println(result);
        /*List<Dish> specialMenu = Arrays.asList(
                new Dish("seasonal fruit", true, 120, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER));
        List<Dish> dishList = specialMenu.stream().filter(dish->dish.getCal() > 300).collect(Collectors.toList());
        dishList.forEach(System.out::println);*/
//        test1();
        test2();


    }

    /**
     * 求数组的平方
     */
    public static void test1() {
        int[] nums = {1, 2, 4, 5};
        int[] ints = Arrays.stream(nums).map(num -> num * num).toArray();
        for (int num : ints) {
            System.out.println(num);
        }
    }

    /**
     * takeWhile
     */
    public static void test2() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        List<Integer> odd = list.stream().filter(x -> x % 2 == 0).collect(Collectors.toList());
        odd.forEach(System.out::println);


    }


}

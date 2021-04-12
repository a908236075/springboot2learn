package com.xiaobo.springboot2.java8.chapter3;

import com.xiaobo.springboot2.java8.chapter2.Apple;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class AppleLambda {

    public static void main(String[] args) {

        applePredicateWeight();
    }

    static void applePredicateWeight() {
        Apple apple = new Apple();
        Apple apple2 = new Apple();
        Apple apple3 = new Apple();
        Apple apple4 = new Apple();
        apple.setWeight(100);
        apple2.setWeight(200);
        apple3.setWeight(300);
        apple4.setWeight(400);

        List<Apple> appleList = Arrays.asList(apple, apple4, apple3, apple2);
        appleList.sort(Comparator.comparingInt(Apple::getWeight));
        appleList.forEach(System.out::println);
    }

}

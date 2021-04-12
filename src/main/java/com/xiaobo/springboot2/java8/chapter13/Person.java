package com.xiaobo.springboot2.java8.chapter13;

public interface Person {

    default void sayHello() {
        System.out.println("Person say hello!");
    }


}

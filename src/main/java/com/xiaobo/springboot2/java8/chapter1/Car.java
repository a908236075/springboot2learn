package com.xiaobo.springboot2.java8.chapter1;

public interface Car {

    void run();

    default void speed() {
        System.out.println("Car 加速");
    }


}

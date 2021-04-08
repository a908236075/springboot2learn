package com.xiaobo.springboot2.java8.chapter1.impl;

import com.xiaobo.springboot2.java8.chapter1.Car;

public class EleCar implements Car {
    @Override
    public void run() {
        System.out.println("跑起来");
    }


    public static void main(String[] args) {
        new EleCar().speed();

    }
}

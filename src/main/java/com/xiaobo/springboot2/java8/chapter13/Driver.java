package com.xiaobo.springboot2.java8.chapter13;

public class Driver implements Person,BigPerson {
    @Override
    public void sayHello() {
        System.out.println("Driver say Hello!");
    }
}

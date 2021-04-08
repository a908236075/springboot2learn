package com.xiaobo.springboot2.entity;

import lombok.Data;

@Data
public class Lovers implements Work{
    private String name;
    private int age;


    @Override
    public void drink(int a) {
        a=4;
        System.out.println("Lovers is drink "+a);
    }

    @Override
    public void meeting(int b) {
        b=4;
        System.out.println("Lover is Meeting"+b);
    }
}

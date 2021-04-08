package com.xiaobo.springboot2.java8.chapter2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dish {


    String chicken;
    boolean b;
    int cal;
    String type;


    public class Type {
        public static final String OTHER = "other";
        public static final String FISH = "fish";
        public static final String MEAT = "meat";
    }
}

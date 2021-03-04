package com.xiaobo.springboot2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author liubt
 * @create 2021-03-03 22:50
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Dog {

    private String name;

    private int age;

}

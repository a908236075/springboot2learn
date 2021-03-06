package com.xiaobo.springboot2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author liubt
 * @create 2021-03-03 22:39
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private String name;

    private int age;

}

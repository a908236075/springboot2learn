package com.xiaobo.springboot2.config;

import com.xiaobo.springboot2.entity.Dog;
import com.xiaobo.springboot2.entity.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

/**
 * @author liubt
 * @create 2021-03-03 22:37
 */
//@Component
@Configuration
@Import(Dog.class)
public class MyConfig {

    @Bean("Student1")
    public Student student() {

        return new Student("zhangsan", 12);

    }


}

package com.xiaobo.springboot2;

import com.xiaobo.springboot2.entity.Dog;
import com.xiaobo.springboot2.entity.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Springboot2learnApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Springboot2learnApplication.class, args);

        Student student1 = run.getBean("Student1", Student.class);
        System.out.println(student1);
        String[] type = run.getBeanNamesForType(Dog.class);
        System.out.println(type);

    }

}

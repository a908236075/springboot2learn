package com.xiaobo.springboot2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;


//@SpringBootApplication
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan("com.xiaobo.springboot2")
@Slf4j
public class Springboot2learnApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Springboot2learnApplication.class, args);

  /*      Student student1 = run.getBean("Student1", Student.class);
        Student student2 = run.getBean("Student1", Student.class);
        System.out.println(student1==student2);
        System.out.println(student1);
        *//*System.out.println(student1);
        String[] type = run.getBeanNamesForType(Dog.class);
        for (String types : type) {
            System.out.println(types);
        }*//*

        // proxyBeanMethods = false
        MyConfig bean = run.getBean(MyConfig.class);
        // student 是方法的名称
        Student student = bean.student();
        Student student3 = bean.student();
        System.out.println("保持组件内所有对象都是单实例:"+(student==student3));*/


        boolean student1 = run.containsBean("Student1");
        System.out.println("Student1: " + student1);

        boolean student2 = run.containsBean("Student2");
        System.out.println("Student2: " + student2);

        boolean tom = run.containsBean("Tom");
        System.out.println("Tom: " + tom);

        Object person = run.getBean("Person");
        log.info("person :"+person);

    }

}

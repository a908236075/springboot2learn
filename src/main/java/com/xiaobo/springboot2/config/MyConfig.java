package com.xiaobo.springboot2.config;

import com.xiaobo.springboot2.entity.Dog;
import com.xiaobo.springboot2.entity.Person;
import com.xiaobo.springboot2.entity.Student;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import javax.xml.crypto.Data;

/**
 * @author liubt
 * @create 2021-03-03 22:37
 */
//@Component
@Configuration(proxyBeanMethods = true)
@EnableConfigurationProperties(Person.class)
@Import(Dog.class)
@ConditionalOnBean(name = "Dog")
//@ConditionalOnMissingClass({"Dog"})
public class MyConfig {

    @Bean("Student1")
    public Student student() {
        return new Student("zhangsan", 12);
    }

    @Bean("Student2")
    public Student student2() {
        return new Student("lisi", 22);
    }

    @Bean("Tom")
    public Dog dogConfig(){
        Dog dog = new Dog();
        dog.setName("tom");
        return dog;
    }
    @Bean("Person")
    public Person personConfig(){
        return new Person();
    }

    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter(){
        HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();
        hiddenHttpMethodFilter.setMethodParam("_m");
        return  hiddenHttpMethodFilter;
    }



}

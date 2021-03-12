package com.xiaobo.springboot2.config;

import com.xiaobo.springboot2.entity.Dog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@Primary
public class DogConfig {

    @Bean("Dog")
    public Dog getDogConfig(){
        Dog dog = new Dog();
        dog.setName("dog");
        return dog;
    }

}

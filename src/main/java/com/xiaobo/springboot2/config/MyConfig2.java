package com.xiaobo.springboot2.config;


import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@ConditionalOnMissingBean({DogConfig.class,MyConfig.class})
@AutoConfigureOrder(10)
public class MyConfig2 {
    // false  每次请求都生成新的对象










}

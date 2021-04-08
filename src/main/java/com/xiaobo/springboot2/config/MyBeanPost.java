package com.xiaobo.springboot2.config;

import com.xiaobo.springboot2.entity.Lovers;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBeanPost implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().equals(Lovers.class))
        System.out.println("Lovers 初始化前置的操作!!!");
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().equals(Lovers.class))
        System.out.println("Lovers 后置的操作");
        return null;
    }
}

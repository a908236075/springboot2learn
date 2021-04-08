package com.xiaobo.springboot2.handler;

import com.xiaobo.springboot2.entity.Lovers;
import lombok.Data;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
@Data
public class LoversHandler implements InvocationHandler {


    private Object object;

    public LoversHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("增强方法执行之前..."+method.getName()+" :的参数为:"+ Arrays.toString(args));
        System.out.println("调用了增强的方法");
        Object invoke = method.invoke(object, args);
        return invoke;
    }
}

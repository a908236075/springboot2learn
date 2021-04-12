package com.xiaobo.springboot2.java8.chapter11;

import java.util.Optional;

public class OptionLambda {

    public static void main(String[] args) {
        Optional<Object> empty = Optional.empty();
        Car car = new Car();
        car.setName("劳斯莱斯");
        Optional<Car> car1 = Optional.of(car);
        car1.ifPresent(System.out::println);
        Car car3 = null;
        Optional<Car> car4 = Optional.of(car3);
//        car4.ifPresent(System.out::println);


    }


}

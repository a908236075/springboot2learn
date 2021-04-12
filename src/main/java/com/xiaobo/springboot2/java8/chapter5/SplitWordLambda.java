package com.xiaobo.springboot2.java8.chapter5;

import com.xiaobo.springboot2.java8.chapter2.Dish;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SplitWordLambda {
    public static void main(String[] args) {
      /*  List<String> wordList = Arrays.asList("Hello", "World");
        List<String[]> wordArray = wordList.stream().map(word -> word.split(" ")).distinct().collect(Collectors.toList());
        List<String> wordList2 = wordList.stream().map(word -> word.split("")).flatMap(Arrays::stream).distinct().collect(Collectors.toList());
        wordList.stream().map(word -> word.split("")).flatMap(Arrays::stream).distinct().collect(Collectors.toList());
        System.out.println(wordList2);
        System.out.println(IntStream.rangeClosed(1, 100).filter(num -> num % 2 == 0).count());*/
//        generatorNoLimitStream();
//        collectionReduce();
        listJoin();


    }

    /**
     * 生成文件流
     */
    static void generatorFileStream() {
        /* try (Stream<String> lines = Files.lines(Paths.get("data.txt"), Charset.defaultCharset())) {
            long count = lines.flatMap(line -> Arrays.stream(line.split(" "))).distinct().count();
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        try {
            List<String> linesString = Files.readAllLines(Paths.get("D:\\develop\\readProject\\springboot2\\src\\main\\resources\\a.txt"), Charset.defaultCharset());
            System.out.println(linesString);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void generatorNoLimitStream() {
        Stream.iterate(0, n -> n + 2).forEach(System.out::println);

    }

    static void collectionReduce() {
        List<Integer> numList = Arrays.asList(2, 3, 4, 5, 6);
        numList.stream().collect(Collectors.maxBy(Integer::compareTo)).ifPresent(System.out::println);
        Double averageNum = numList.stream().collect(Collectors.averagingInt(Integer::intValue));
        System.out.println(averageNum);
        IntSummaryStatistics intSummaryStatistics = numList.stream().collect(Collectors.summarizingInt(Integer::intValue));
        System.out.println(intSummaryStatistics);
        System.out.println(intSummaryStatistics.getMin());
    }

    static void listJoin(){
        List<String> strList = Arrays.asList("a", "b", "c", "d", "e");
        String newList = strList.stream().collect(Collectors.joining(","));
        System.out.println(newList);


    }


}

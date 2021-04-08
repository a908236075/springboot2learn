package com.xiaobo.springboot2.java8.chapter5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TraderLambda {

    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        // 找出 2011年发生的所有交易，并按交易额排序（从低到高）。

        List<Transaction> transactionList1 = transactions.stream().filter(x -> x.getYear() == 2011).sorted(Comparator.comparingInt(Transaction::getValue)).collect(Collectors.toList());
        transactionList1.forEach(System.out::println);
        System.out.println("=======================");
        // 交易员都在哪些不同的城市工作过
        List<String> cityList = transactions.stream().map(x -> x.getTrader()).map(y -> y.getCity()).distinct().collect(Collectors.toList());
        cityList.forEach(System.out::println);
        System.out.println("=======================");
        // 查找所有来自于剑桥的交易员，并按姓名排序
        List<Trader> traderList = transactions.stream().map(x -> x.getTrader()).filter(y -> y.getCity().equals("Cambridge")).distinct().sorted(Comparator.comparing(Trader::getName)).collect(Collectors.toList());
        traderList.forEach(System.out::println);
        System.out.println("=======================");
        // 返回所有交易员的姓名字符串，按字母顺序排序。
        List<String> nameList = transactions.stream().map(x -> x.getTrader()).map(x -> x.getName()).sorted().collect(Collectors.toList());
        nameList.forEach(System.out::println);
        System.out.println("=======================");
        // 有没有交易员是在米兰工作的？
        boolean milan = transactions.stream().anyMatch(x -> x.getTrader().getCity().equals("Milan"));
        System.out.println(milan);
        System.out.println("=======================");
        // 打印生活在剑桥的交易员的所有交易额
        Integer camTotalValue = transactions.stream().filter(y -> y.getTrader().getCity().equals("Cambridge")).map(x -> x.getValue()).reduce(0, Integer::sum);
        System.out.println(camTotalValue);
        System.out.println("=======================");
        //  所有交易中，最高的交易额是多少？
        transactions.stream().map(x -> x.getValue()).reduce(Integer::min).ifPresent(System.out::println);
        transactions.stream().map(x -> x.getValue()).reduce(Integer::min).ifPresent(System.out::println);
        System.out.println("=======================");
        // 总数
        Long numberTotal = transactions.stream().collect(Collectors.counting());
        long count = transactions.stream().count();
        System.out.println(numberTotal);
        System.out.println(count);
        System.out.println("=======================");

    }
}

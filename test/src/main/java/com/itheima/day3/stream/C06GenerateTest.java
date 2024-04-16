package com.itheima.day3.stream;

import java.util.concurrent.ThreadLocalRandom;

public class C06GenerateTest {
    public static void main(String[] args) {

        // 1. IntStream.range

//        IntStream.range(1, 10).forEach(System.out::println);
//        IntStream.rangeClosed(1, 9).forEach(System.out::println);

        // 2. IntStream.iterate  生成 1 3 5 7 9 ... 奇数序列    可以根据上一个元素值来生成当前元素
//        IntStream.iterate(1, x -> x + 2).limit(10).forEach(System.out::println);
//        IntStream.iterate(1, x -> x <= 9, x -> x + 2).forEach(System.out::println);

        // 3. IntStream.generate
//        IntStream.generate(()-> ThreadLocalRandom.current().nextInt(100)).limit(5).forEach(System.out::println);

        ThreadLocalRandom.current().ints(5, 0, 100).forEach(System.out::println);
    }
}

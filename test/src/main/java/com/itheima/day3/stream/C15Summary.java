package com.itheima.day3.stream;

import java.util.stream.Stream;

public class C15Summary {
    public static void main(String[] args) {
        /*
           掌握 Stream 流的特性

            1. 一次使用
            2. 两类操作（中间操作 lazy 懒惰， 终结操作 eager 迫切）

         */
        Stream<Integer> s1 = Stream.of(1, 3, 5); // 水滴
        // -----------------------------------    -------------------------  阀门
        s1
                .map(x -> x + 1)                        // 水管
                .filter(x -> x <= 5)                    // 水管
                .forEach(x -> System.out.println(x));   // 水管 总阀门
    }
}

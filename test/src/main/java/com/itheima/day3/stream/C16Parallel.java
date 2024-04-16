package com.itheima.day3.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collector;
import java.util.stream.Stream;

// 并行流
public class C16Parallel {
    public static void main(String[] args) {

        /*List<Integer> collect = Stream.of(1, 2, 3, 4)
                .collect(Collector.of(
                        () -> {
                            System.out.printf("%-12s %s%n",simple(),"create");
                            return new ArrayList<Integer>();
                        },
                        (list, x) -> {
                            List<Integer> old = new ArrayList<>(list);
                            list.add(x);
                            System.out.printf("%-12s %s.add(%d)=>%s%n",simple(), old, x, list);
                        },
                        (list1, list2) -> {
                            List<Integer> old = new ArrayList<>(list1);
                            list1.addAll(list2);
                            System.out.printf("%-12s %s.add(%s)=>%s%n", simple(),old, list2, list1);
                            return list1;
                        },
                        list -> null,
                        Collector.Characteristics.IDENTITY_FINISH
                ));

                */


        /*
            1) 数据量问题: 数据量大时才建议用并行流
            2) 线程会无限增加吗: 跟 cpu 能处理的线程数相关
            3) 收尾的意义: 转不可变集合、StringBuilder 转 String ...
            4) 是否线程安全: 不会有线程安全问题
            5) 特性：
                是否需要收尾（默认收尾）
                是否需要保证顺序（默认保证）
                容器是否支持并发（默认不需要支持）

                到达选择哪一种？
                    A. Characteristics.CONCURRENT + Characteristics.UNORDERED + 线程安全容器  并发量大性能可能会受影响
                    B. 默认 + 线程不安全容器                                                   占用内存多，合并多也会影响性能

         */
        List<Integer> collect = Stream.of(1, 2, 3, 4)
                .parallel()
                .collect(Collector.of(
                        () -> {
                            System.out.printf("%-12s %s%n", simple(), "create");
                            return new Vector<Integer>();
                        },                                                                          // 1.如何创建容器
                        (list, x) -> {
                            List<Integer> old = new ArrayList<>(list);
                            list.add(x);
                            System.out.printf("%-12s %s.add(%d)=>%s%n", simple(), old, x, list);
                        },                                                                          // 2.如何向容器添加数据
                        (list1, list2) -> {
                            List<Integer> old = new ArrayList<>(list1);
                            list1.addAll(list2);
                            System.out.printf("%-12s %s.add(%s)=>%s%n", simple(), old, list2, list1);
                            return list1;
                        },                                                                          // 3.如何合并两个容器的数据
                        list -> {
                            System.out.printf("%-12s finish: %s=>%s%n", simple(), list, list);
                            return Collections.unmodifiableList(list);
                        }                                                                           // 4.收尾
                        , Collector.Characteristics.IDENTITY_FINISH                                 // 不需要收尾
                        , Collector.Characteristics.UNORDERED                                       // 不需要保证顺序
                        , Collector.Characteristics.CONCURRENT                                      // 容器需要支持并发
                ));

        System.out.println(collect);
        collect.add(100);
        System.out.println(collect);
    }

    private static String simple() {
        String name = Thread.currentThread().getName();
        int idx = name.indexOf("worker");
        if (idx > 0) {
            return name.substring(idx);
        }
        return name;
    }
}

package com.itheima.day3.stream;

import java.util.Comparator;
import java.util.stream.Stream;

public class C09SortTest {
    public static void main(String[] args) {

        // 去重
        /*IntStream.of(1, 2, 3, 1, 2, 3, 3, 4, 5)
                .distinct()
                .forEach(System.out::println);*/

        // 排序
        Stream.of(
                new Hero("令狐冲", 90),
                new Hero("风清扬", 98),
                new Hero("独孤求败", 100),
                new Hero("方证", 92),
                new Hero("东方不败", 98),
                new Hero("冲虚", 90),
                new Hero("向问天", 88),
                new Hero("任我行", 92),
                new Hero("不戒", 88)
        )
//        .sorted((a, b) -> a.strength() < b.strength() ? -1 : a.strength() ==  b.strength() ? 0 : 1)
//        .sorted((a, b) -> Integer.compare(a.strength(), b.strength()))
//        .sorted(Comparator.comparingInt(h->h.strength()))
//        .sorted(Comparator.comparingInt(Hero::strength).reversed()) // 按武力降序
        .sorted(Comparator.comparingInt(Hero::strength).reversed().thenComparingInt(h->h.name().length())) // 按武力降序，武力相等的按名字长度升序
        .forEach(System.out::println);
    }

    record Hero(String name, int strength) {

    }
}

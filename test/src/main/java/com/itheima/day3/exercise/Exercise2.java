package com.itheima.day3.exercise;

import java.util.List;
import java.util.stream.Stream;

public class Exercise2 {
    public static void main(String[] args) {
        Stream.of(
                        new Order(1, List.of(
                                new Item(6499, 1, "HUAWEI MateBook 14s"),
                                new Item(6999, 1, "HUAWEI Mate 60 Pro"),
                                new Item(1488, 1, "HUAWEI WATCH GT 4")
                        )),
                        new Order(1, List.of(
                                new Item(8999, 1, "Apple MacBook Air 13"),
                                new Item(7999, 1, "Apple iPhone 15 Pro"),
                                new Item(2999, 1, "Apple Watch Series 9")
                        ))
                )
                .flatMap(order -> order.items().stream())
                .forEach(System.out::println);
    }

    record Order(int id, List<Item> items) {
    }

    record Item(double unitPrice, int count, String name) {
    }
}

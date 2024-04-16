package com.itheima.day3.exercise;

import java.util.function.Predicate;
import java.util.stream.Stream;

public class Exercise1 {

    public static void main(String[] args) {
        Stream.of(
                        new Fruit("草莓", "Strawberry", "浆果", "红色"),
                        new Fruit("桑葚", "Mulberry", "浆果", "紫色"),
                        new Fruit("杨梅", "Waxberry", "浆果", "红色"),
                        new Fruit("核桃", "Walnut", "坚果", "棕色"),
                        new Fruit("草莓", "Peanut", "坚果", "棕色"),
                        new Fruit("蓝莓", "Blueberry", "浆果", "蓝色")
                )
                .filter(Fruit::condition1)
                .filter(Fruit::condition2)
                .forEach(System.out::println);
    }

    record Fruit(String cname, String name, String category, String color) {
        static boolean condition1(Fruit fruit) {
            Predicate<Fruit> step1 = f -> f.category().equals("浆果");
            return step1.and(f -> f.color().equals("蓝色")).test(fruit);
        }

        boolean condition2() {
            Predicate<Fruit> step1 = f -> f.category().equals("浆果");
            return step1.and(f -> f.color().equals("蓝色")).test(this);
        }
    }
}

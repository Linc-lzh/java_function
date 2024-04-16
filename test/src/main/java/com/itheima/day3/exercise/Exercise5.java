package com.itheima.day3.exercise;


import java.util.List;
import java.util.stream.Stream;

public class Exercise5 {
    public static void main(String[] args) {
        Stream.of(
                        new City("陕西",
                                List.of(
                                        new City("西安", List.of(
                                                new City("碑林区", List.of(), List.of(new User("张三"))),
                                                new City("雁塔区", List.of(), List.of(new User("李四"))),
                                                new City("高新区", List.of(), List.of(new User("王五")))
                                        ), List.of()),
                                        new City("宝鸡", List.of(
                                                new City("金台区", List.of(), List.of(new User("赵六")))
                                        ), List.of())
                                ),
                                List.of()),
                        new City("北京",
                                List.of(
                                        new City("昌平区", List.of(), List.of(new User("钱七"))),
                                        new City("海淀区", List.of(), List.of(new User("邓八")))
                                ),
                                List.of())
                )
                .flatMap(c -> Stream.concat(c.children().stream(), c.users().stream()))
                .flatMap(x ->
                {
//                    System.out.println("【2】"+x);
                    return switch (x) {
                        case City c -> Stream.concat(c.children().stream(), c.users().stream());
                        default -> Stream.of(x);
                    };
                })
                .flatMap(x ->
                {
//                    System.out.println("【3】"+x);
                    return switch (x) {
                        case City c -> Stream.concat(c.children().stream(), c.users().stream());
                        default -> Stream.of(x);
                    };
                })
                .forEach(x -> {
                    System.out.println("【4】" + x);
                });
    }

    record City(String name, List<City> children, List<User> users) {
    }

    record User(String name) {
    }
}

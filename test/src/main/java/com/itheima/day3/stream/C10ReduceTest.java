package com.itheima.day3.stream;

import java.util.stream.Stream;

/*
    化简：两两合并，只剩一个
    适合：最大值、最小值、求和、求个数...
        .reduce((p, x) -> r)        p 上次的合并结果， x 当前元素， r 本次合并结果
        .reduce(init, (p, x) -> r)
        .reduce(init, (p, x) -> r, (r1, r2) -> r)
 */
public class C10ReduceTest {
    record Hero(String name, int strength) {

    }

    public static void main(String[] args) {
        Stream<Hero> stream = Stream.of(
                new Hero("令狐冲", 90),
                new Hero("风清扬", 98),
                new Hero("独孤求败", 100),
                new Hero("方证", 92),
                new Hero("东方不败", 98),
                new Hero("冲虚", 90),
                new Hero("向问天", 88),
                new Hero("任我行", 92),
                new Hero("不戒", 88)
        );

        // 1) 求武力最高的 hero
//        Optional<Hero> result = stream.reduce((h1, h2) -> h1.strength() > h2.strength() ? h1 : h2);
//        Hero result = stream.reduce(new Hero("-", -1), (h1, h2) -> h1.strength() > h2.strength() ? h1 : h2);
//        System.out.println(result);

        // 2) 求高手总数
//        System.out.println(stream.map(h -> 1).reduce(0, (a, b) -> a + b));

//        System.out.println(stream.count());
//        System.out.println(stream.max(Comparator.comparingInt(Hero::strength)));
//        System.out.println(stream.min(Comparator.comparingInt(Hero::strength)));
//        System.out.println(stream.mapToInt(Hero::strength).sum());
        System.out.println(stream.mapToInt(Hero::strength).average());
    }
}

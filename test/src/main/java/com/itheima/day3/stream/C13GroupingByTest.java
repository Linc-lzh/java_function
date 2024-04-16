package com.itheima.day3.stream;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class C13GroupingByTest {
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

        /*
         1. mapping(x->y, dc)  需求：根据名字长度分组，分组后组内只保留他们的武力值
             new Hero("令狐冲", 90)->90
             dc 下游收集器 down collector

             stream.collect(groupingBy(h -> h.name().length(), mapping(h -> h.strength(), toList())));
         */

        /*
         2. filtering(x->boolean, dc)  需求：根据名字长度分组，分组后组内过滤掉武力小于 90 的

         在分组收集的过程中，执行过滤
         stream.collect(groupingBy(h -> h.name().length(), filtering(h -> h.strength() >= 90, toList())));
         先过滤，再来分组收集
         stream.filter(h -> h.strength() >= 90).collect(groupingBy(h -> h.name().length(), toList()));
         */

        /*
         3. flatMapping(x->substream, dc)     需求：根据名字长度分组，分组后组内保留人名，并且人名切分成单个字符

         "令狐冲".chars().mapToObj(Character::toString).forEach(System.out::println);

         stream.collect(groupingBy(h -> h.name().length(),
                flatMapping(h -> h.name().chars().mapToObj(Character::toString), toList())));
         */

        /*
         4. counting() 需求：根据名字长度分组，分组后求每组个数

         stream.collect(groupingBy(h -> h.name().length(), counting()));
         */

        /*
          5. minBy((a,b)->int) 需求：根据名字长度分组，分组后求每组武功最低的人
          6. maxBy((a,b)->int) 需求：根据名字长度分组，分组后求每组武功最高的人

          stream.collect(groupingBy(h -> h.name().length(), maxBy(Comparator.comparingInt(Hero::strength))));
         */

        /*
          7. summingInt(x->int)            需求：根据名字长度分组，分组后求每组武力和
          8. averagingDouble(x->double)    需求：根据名字长度分组，分组后求每组武力平均值

          stream.collect(groupingBy(h -> h.name().length(), averagingDouble(h -> h.strength())));
         */

        /*
         9. reducing(init,(p,x)->r)

         求和
          stream.collect(groupingBy(h -> h.name().length(), mapping(h -> h.strength(), reducing(0, (p, x) -> p + x))));
         求个数
          stream.collect(groupingBy(h -> h.name().length(), mapping(h -> 1, reducing(0, (p, x) -> p + x))));
        */

        // 求平均，缺少 finisher
        Map<Integer, double[]> collect = stream.collect(groupingBy(h -> h.name().length(),
                mapping(h -> new double[]{h.strength(), 1},
                        reducing(new double[]{0, 0}, (p, x) -> new double[]{p[0] + x[0], p[1] + x[1]}))));
        for (Map.Entry<Integer, double[]> e : collect.entrySet()) {
            System.out.println(e.getKey() + ":" + Arrays.toString(e.getValue()));
        }
    }
}

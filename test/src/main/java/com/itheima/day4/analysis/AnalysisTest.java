package com.itheima.day4.analysis;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class AnalysisTest {
    /*
        数据格式
        0       1       2       3       4       5       6     7      8        9     10    11
        序号    下单时间  订单编号  商品编号 类别编号  类别码   品牌   价格   用户编号   年龄   性别   地区
     */
    static final int INDEX = 0;
    static final int TIME = 1;
    static final int ORDER_ID = 2;
    static final int PRODUCT_ID = 3;
    static final int CATEGORY_ID = 4;
    static final int CATEGORY_CODE = 5;
    static final int BRAND = 6;
    static final int PRICE = 7;
    static final int USER_ID = 8;
    static final int USER_AGE = 9;
    static final int USER_SEX = 10;
    static final int USER_REGION = 11;
    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");

    public static void main(String[] args) {
        case8();
    }

    // 不同年龄段女性所下不同类别订单
    private static void case8() {
        try (Stream<String> lines = Files.lines(Path.of("./data.txt"))) {
            Map<String, Map<String, Long>> map = lines.skip(1)
                    .map(line -> line.split(","))
                    .filter(array -> array[USER_SEX].equals("女"))
                    .filter(array -> !array[CATEGORY_CODE].isEmpty())
                    .collect(groupingBy(AnalysisTest::ageRange,
                            groupingBy(AnalysisTest::firstCategory, TreeMap::new, counting())));

            for (Map.Entry<String, Map<String, Long>> e1 : map.entrySet()) {
                for (Map.Entry<String, Long> e2 : e1.getValue().entrySet()) {
                    System.out.printf("%-12s%-15s%d%n", e1.getKey(), e2.getKey(), e2.getValue());
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static String ageRange(String[] array) {
        int age = Double.valueOf(array[USER_AGE]).intValue();
        if (age < 18) {
            return "[0,18)";
        } else if (age < 30) {
            return "[18,30)";
        } else if (age < 50) {
            return "[30,50)";
        } else {
            return "[50,∞)";
        }
    }

    static String priceRange(Double price) {
        if (price < 100) {
            return "[0,100)";
        } else if (price >= 100 && price < 500) {
            return "[100,500)";
        } else if (price >= 500 && price < 1000) {
            return "[500,1000)";
        } else {
            return "[1000,∞)";
        }
    }

    // 7) 按价格区间统计销量
    private static void case7() {
        try (Stream<String> lines = Files.lines(Path.of("./data.txt"))) {
            Map<String, Long> collect = lines.skip(1)
                    .map(line -> line.split(","))
                    .map(array -> Double.valueOf(array[PRICE]))
                    .collect(groupingBy(AnalysisTest::priceRange, counting()));

            for (Map.Entry<String, Long> e : collect.entrySet()) {
                System.out.println(e);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 6.1) 按类别统计销量
    private static void case61() {
        try (Stream<String> lines = Files.lines(Path.of("./data.txt"))) {
            Map<String, Long> collect = lines.skip(1)
                    .map(line -> line.split(","))
                    .filter(array -> !array[CATEGORY_CODE].isEmpty())
                    .collect(groupingBy(array -> array[CATEGORY_CODE], TreeMap::new, counting()));

            for (Map.Entry<String, Long> e : collect.entrySet()) {
                System.out.println(e);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static String firstCategory(String[] array) {
        String c = array[CATEGORY_CODE];
        int idx = c.indexOf(".");
        return c.substring(0, idx);
    }

    // 6.2) 按一级类别统计销量
    private static void case62() {
        try (Stream<String> lines = Files.lines(Path.of("./data.txt"))) {
            Map<String, Long> collect = lines.skip(1)
                    .map(line -> line.split(","))
                    .filter(array -> !array[CATEGORY_CODE].isEmpty())
                    .collect(groupingBy(AnalysisTest::firstCategory, TreeMap::new, counting()));

            for (Map.Entry<String, Long> e : collect.entrySet()) {
                System.out.println(e);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 5.1) 每个地区下单最多的用户
    private static void case51() {
        try (Stream<String> lines = Files.lines(Path.of("./data.txt"))) {
            Map<String, Map<String, Long>> collect = lines.skip(1)
                    .map(line -> line.split(","))
                    .collect(groupingBy(array -> array[USER_REGION], groupingBy(array -> array[USER_ID], counting())));

            collect.entrySet().stream()
                    .map(e -> Map.entry(
                            e.getKey(),
                            e.getValue().entrySet().stream()
                                    .max(Map.Entry.comparingByValue())
                    ))
                    .forEach(System.out::println);
            /*for (Map.Entry<String, Map<String, Long>> e1 : collect.entrySet()) {
                System.out.println(e1.getKey());
                System.out.println("------------------");
                int i = 0;
                for (Map.Entry<String, Long> e2 : e1.getValue().entrySet()) {
                    if (i >= 5) {
                        break;
                    }
                    System.out.println(e2);
                    i++;
                }
            }*/
            /*
                上海              ---> 上海
                    1   5        ---> 2   10
                    2   10
                    3   3
                    ...
                广东              ---> 广东
                ...
             */
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 5.2) 每个地区下单最多的前三用户
    private static void case52() {
        try (Stream<String> lines = Files.lines(Path.of("./data.txt"))) {
            Map<String, Map<String, Long>> collect = lines.skip(1)
                    .map(line -> line.split(","))
                    .collect(groupingBy(array -> array[USER_REGION], groupingBy(array -> array[USER_ID], counting())));

            collect.entrySet().stream()
                    .map(e -> Map.entry(
                            e.getKey(),
//                            e.getValue().entrySet().stream()
//                                    .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
//                                    .limit(3)
//                                    .collect(toList())
                            e.getValue().entrySet().stream()
                                    .collect(
                                            () -> new MyQueue<Map.Entry<String, Long>>(Map.Entry.comparingByValue(), 3),
                                            MyQueue::offer,
                                            MyQueue::addAll
                                    )
                    ))
                    .forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 4) 下单最多的前十名用户
    private static void case41() {
        try (Stream<String> lines = Files.lines(Path.of("./data.txt"))) {
            Map<String, Long> collect = lines.skip(1)
                    .map(line -> line.split(","))
                    .collect(groupingBy(array -> array[USER_ID], counting()));
//            for (Map.Entry<String, Long> e : collect.entrySet()) {
//                System.out.println(e);M
//            }
            collect.entrySet().stream()
                    .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                    .limit(10)
                    .forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    static class MyQueue<E> extends PriorityQueue<E> {
        private final int max;

        public MyQueue(Comparator<? super E> comparator, int max) {
            super(comparator);
            this.max = max;
        }

        @Override
        public boolean offer(E e) {
            boolean r = super.offer(e);
            if (this.size() > max) {
                this.poll();
            }
            return r;
        }
    }

    private static void case42() {
        try (Stream<String> lines = Files.lines(Path.of("./data.txt"))) {
            MyQueue<Map.Entry<String, Long>> queue = lines.skip(1)
                    .map(line -> line.split(","))
                    .collect(groupingBy(array -> array[USER_ID], counting()))
                    .entrySet().stream()
                    .collect(
                            () -> new MyQueue<>(Map.Entry.comparingByValue(), 10),
                            MyQueue::offer,
                            AbstractQueue::addAll
                    );

            while (!queue.isEmpty()) {
                System.out.println(queue.poll());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 3) 统计销售量最高的商品
    private static void case3() {
        try (Stream<String> lines = Files.lines(Paths.get("./data.txt"))) {
            lines.skip(1)
                    .map(line -> line.split(","))
                    .collect(groupingBy(array -> array[PRODUCT_ID], counting()))
                    .entrySet().stream()
                    .max(Map.Entry.comparingByValue())
                    .ifPresent(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 2) 统计销售量最高的月份
    private static void case2() {
        try (Stream<String> lines = Files.lines(Path.of("./data.txt"))) {
            lines.skip(1)
                    .map(line -> line.split(","))
                    .collect(groupingBy(array -> YearMonth.from(formatter.parse(array[TIME])), counting()))
                    .entrySet().stream()
//                    .max(Comparator.comparingLong(e -> e.getValue()));
                    .max(Map.Entry.comparingByValue())
                    .ifPresent(x -> System.out.println(x));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 1) 统计每月的销售量
    private static void case1() {
        try (Stream<String> lines = Files.lines(Path.of("./data.txt"))) {
            Map<YearMonth, Long> collect = lines.skip(1)
                    .map(line -> line.split(","))
                    .collect(groupingBy(array -> YearMonth.from(formatter.parse(array[TIME])), TreeMap::new, counting()));
            for (Map.Entry<YearMonth, Long> e : collect.entrySet()) {
                System.out.println(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}

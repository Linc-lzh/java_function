package com.itheima.day2.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Exercise3 {

    public static void main(String[] args) {
        List<Integer> result = filter(List.of(1, 2, 3, 4, 5, 6), (Integer number) -> (number & 1) == 1);
        System.out.println(result);
    }

    static List<Integer> filter(List<Integer> list, Predicate<Integer> predicate) {
        List<Integer> result = new ArrayList<>();
        for (Integer number : list) {
            // 筛选：判断是否是偶数，但以后可能改变筛选规则
            if(predicate.test(number)) {
                result.add(number);
            }
        }
        return result;

        /*
            (Integer number) -> (number & 1) == 0
         */
    }

    static List<String> map(List<Integer> list) {
        List<String> result = new ArrayList<>();
        for (Integer number : list) {
            // 转换：将数字转为字符串，但以后可能改变转换规则
            result.add(String.valueOf(number));
        }
        return result;
    }

    static void consume(List<Integer> list) {
        for (Integer number : list) {
            // 消费：打印，但以后可能改变消费规则
            System.out.println(number);
        }
    }

    static List<Integer> supply(int count) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            // 生成：随机数，但以后可能改变生成规则
            result.add(ThreadLocalRandom.current().nextInt());
        }
        return result;
    }
}

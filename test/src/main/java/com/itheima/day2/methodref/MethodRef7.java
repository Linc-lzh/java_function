package com.itheima.day2.methodref;

import java.util.function.Consumer;
import java.util.function.Function;

public class MethodRef7 {
    public static void main(String[] args) {
        Consumer<Object> x = MethodRef7::print1;
        Function<Object, Integer> y = MethodRef7::print2;
        Consumer<Object> z = MethodRef7::print2;
    }

    static void print1(Object obj) {
        System.out.println(obj);
    }

    static int print2(Object obj) {
        System.out.println(obj);
        return 1;
    }
}

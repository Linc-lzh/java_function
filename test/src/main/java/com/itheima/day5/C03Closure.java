package com.itheima.day5;

import java.lang.reflect.Method;
import java.util.function.BinaryOperator;

// -Djdk.invoke.LambdaMetafactory.dumpProxyClassFiles
public class C03Closure {
    public static void main(String[] args) {
        int c = 10;
        BinaryOperator<Integer> lambda = (a, b) -> a + b + c; // invoke dynamic  new C03Closure$$Lambda(10)
        test(lambda);
        for (Method method : C03Closure.class.getDeclaredMethods()) {
            System.out.println(method);
        }
        // 1. 方法
        // 2. 类和对象
    }

    static void test(BinaryOperator<Integer> lambda) {

    }

    final static class C03Closure$$Lambda implements BinaryOperator {
        private final int c;

        private C03Closure$$Lambda(int c) {
            this.c = c;
        }

        public Object apply(Object a, Object b) {
            return C03Closure.lambda$main$1(this.c, (Integer)a, (Integer)b);
        }
    }

    static private Integer lambda$main$1(int c, Integer a, Integer b) {
        return a + b + c;
    }
}

package com.itheima.day5;

import java.util.function.BinaryOperator;

// -Djdk.invoke.LambdaMetafactory.dumpProxyClassFiles
public class C05Closure {

    private int c = 10;
    BinaryOperator<Integer> lambda = (a, b) -> a + b + c;

    public static void main(String[] args) {
        new C05Closure();
    }

    /*final class C05Closure$$Lambda implements BinaryOperator {
        private final C05Closure c;

        private C05Closure$$Lambda(C05Closure c) {
            this.c = c;
        }

        public Object apply(Object a, Object b) {
            return this.c.lambda$new$1((Integer)a, (Integer)b);
        }
    }

    private Integer lambda$new$1(Integer a, Integer b) {
        return a + b + c;
    }*/
}

package com.itheima.day5;

import java.lang.reflect.Method;
import java.util.function.BinaryOperator;

// -Djdk.invoke.LambdaMetafactory.dumpProxyClassFiles
public class C04Closure {
    static int c = 10;
    public static void main(String[] args) {
        BinaryOperator<Integer> lambda = (a, b) -> a + b + c;
        test(lambda);
        /*System.out.println(lambda.apply(1, 2));
        c = 20;
        System.out.println(lambda.apply(1, 2));*/
        for (Method method : C04Closure.class.getDeclaredMethods()) {
            System.out.println(method);
        }
    }

    static void test(BinaryOperator<Integer> lambda) {

    }

    static final class C04Closure$$Lambda implements BinaryOperator {
        private C04Closure$$Lambda() {
        }

        public Object apply(Object a, Object b) {
            return C04Closure.lambda$main$1((Integer)a, (Integer)b);
        }
    }

    private static Integer lambda$main$1(Integer a, Integer b) {
        return a + b + C04Closure.c;
    }
}

package com.itheima.test;

import java.lang.invoke.*;
import java.util.function.BinaryOperator;

public class TestLambda2 {
    public static void main(String[] args) throws Throwable {
//        int c = 10;
//        test((a, b) -> a + b + c);

        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType interfaceType = MethodType.methodType(BinaryOperator.class, int.class);
        MethodType interfaceMethodType = MethodType.methodType(Object.class, Object.class, Object.class);
        MethodType implementsMethodType = MethodType.methodType(Integer.class, int.class, Integer.class, Integer.class);

        MethodHandle implementsMethod = lookup.findStatic(TestLambda2.class, "lambda$main$1", implementsMethodType);

        MethodType lambdaType = MethodType.methodType(Integer.class, Integer.class, Integer.class);
        CallSite callSite = LambdaMetafactory.metafactory(lookup,
                "apply", interfaceType, interfaceMethodType,
                implementsMethod,
                lambdaType);

        BinaryOperator<Integer> lambda = (BinaryOperator) callSite.getTarget().invoke(10);
        test(lambda);
    }

    static Integer lambda$main$1(int c, Integer a, Integer b) {
        return a + b + c;
    }

    static void test(BinaryOperator<Integer> lambda) {
        System.out.println(lambda.apply(1, 2));
    }
}

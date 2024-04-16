package com.itheima.test;

import java.lang.invoke.*;
import java.util.function.BinaryOperator;

public class TestLambda4 {
    static class MyRef {
        int age;

        public MyRef(int age) {
            this.age = age;
        }
    }
    public static void main(String[] args) throws Throwable {
        /*MyRef ref = new MyRef(10);
        test((a, b) -> a + b + ref.age);*/

        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType interfaceType = MethodType.methodType(BinaryOperator.class, MyRef.class);
        MethodType interfaceMethodType = MethodType.methodType(Object.class, Object.class, Object.class);
        MethodType implementsMethodType = MethodType.methodType(Integer.class, MyRef.class, Integer.class, Integer.class);

        MethodHandle implementsMethod = lookup.findStatic(TestLambda4.class, "lambda$main$1", implementsMethodType);

        MethodType lambdaType = MethodType.methodType(Integer.class, Integer.class, Integer.class);
        CallSite callSite = LambdaMetafactory.metafactory(lookup,
                "apply", interfaceType, interfaceMethodType,
                implementsMethod,
                lambdaType);

        BinaryOperator<Integer> lambda = (BinaryOperator) callSite.getTarget().bindTo(new MyRef(20)).invoke();
        test(lambda);
    }

    static Integer lambda$main$1(MyRef c, Integer a, Integer b) {
        return a + b + c.age;
    }

    static void test(BinaryOperator<Integer> lambda) {
        System.out.println(lambda.apply(1, 2));
    }
}

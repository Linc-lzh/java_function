package com.itheima.test;

import java.lang.invoke.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;

public class TestLambda3 {
    public static void main(String[] args) throws Throwable {
//        test(String::toLowerCase);
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType interfaceType = MethodType.methodType(Function.class);
        MethodType interfaceMethodType = MethodType.methodType(Object.class, Object.class);
        MethodHandle implementsMethod = lookup.findVirtual(String.class, "toLowerCase", MethodType.methodType(String.class));
        MethodType lambdaType = MethodType.methodType(String.class, String.class);
        CallSite callSite = LambdaMetafactory.metafactory(lookup,
                "apply", interfaceType, interfaceMethodType,
                implementsMethod,
                lambdaType);

        Function<String, String> lambda = (Function<String, String>) callSite.getTarget().invoke();
        System.out.println(lambda.apply("Tom"));
    }

    static void test(Function<String,String> lambda) {
        System.out.println(lambda.apply("Tom"));
    }
}

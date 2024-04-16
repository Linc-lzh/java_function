package com.itheima.day4.framework.mybatis;

import java.io.*;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.function.Function;

public class TestSerializable {
    public static void main(String[] args) throws Exception {
        // 可序列化的函数对象
        Type1 lambda = (Type1 & Serializable) stu -> stu.getName();

        // 函数对象 <=> 字节码   会额外存储类和方法的信息, 运行时就可以根据这些信息找到属性, 从而进一步确定【列名】
        /*for (Method method : lambda.getClass().getDeclaredMethods()) {
            System.out.println(method);
        }*/
        SerializedLambda invoke = (SerializedLambda) lambda.getClass().getDeclaredMethod("writeReplace").invoke(lambda);
        // invoke 是新对象，包含了原始函数对象的字节码，还包含了类和方法的额外信息
        System.out.println(invoke.getClass());

        System.out.println(invoke.getCapturingClass()); // 哪个类使用了这个函数对象
        System.out.println(invoke.getImplClass());      // 哪个类实现了函数对象的逻辑
        System.out.println(invoke.getImplMethodName()); // 哪个方法实现了函数对象的逻辑
    }

    interface Type1 {
        String abc(Student student);
    }
}

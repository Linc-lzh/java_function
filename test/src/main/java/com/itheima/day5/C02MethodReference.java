package com.itheima.day5;

import java.lang.invoke.*;
import java.util.function.Function;

// -Djdk.invoke.LambdaMetafactory.dumpProxyClassFiles
public class C02MethodReference {

    public static void main(String[] args) throws Throwable {
        // 方法引用是一种语法糖，它仍然会被翻译成 类、对象、方法
        // 1. 方法从哪来
        // 2. 类、对象从哪来

        Function<Student, String> func = Student::getName; // stu->stu.getName()

        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle impl = lookup.findVirtual(Student.class, "getName", MethodType.methodType(String.class));
        CallSite cs = LambdaMetafactory.metafactory(
                lookup,
                "apply",
                MethodType.methodType(Function.class),
                MethodType.methodType(Object.class, Object.class),
                impl,
                MethodType.methodType(String.class, Student.class)
        );

        Function<Student, String> invoke = (Function<Student, String>) cs.getTarget().invoke();
        Student stu = new Student();
        stu.name = "张三";
        System.out.println(invoke.apply(stu));
    }

    static final class MyMethodReference implements Function<Student, String> {

        @Override
        public String apply(Student student) {
            return student.getName();
        }
    }


    static class Student {
        private String name;

        public String getName() {
            return name;
        }
    }
}

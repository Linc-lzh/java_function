package com.itheima.day2.methodref;


import java.util.function.*;

public class MethodRef0 {
    public static void main(String[] args) {
        IntUnaryOperator x = Math::abs;
        IntBinaryOperator y = Math::max;
        Function<String, Integer> z = Integer::parseInt;

        Consumer<Student> a = Student::getName;
        BiConsumer<Student, String> b = Student::setName;
    }

    static class Student {
        String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

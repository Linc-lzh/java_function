package com.itheima.day2.closure;

public class ClosureTest1 {

    @FunctionalInterface
    interface Lambda {
        int op(int y);
    }

    static void highOrder(Lambda lambda) {
        System.out.println(lambda.op(1));
    }

    public static void main(String[] args) {
        /*
            函数对象 (int y) -> x + y 与它外部的变量 x 形成了闭包

            effective final
         */
//        int x = 10;
//        highOrder((int y) -> x + y);

        Student stu = new Student(20);
        Lambda lambda = y -> y + stu.d;
        highOrder(lambda);

        stu.d = 40;
        highOrder(lambda);
    }

    static class Student {
        int d;

        public Student(int d) {
            this.d = d;
        }
    }

    static int a = 1;
    int b = 2;

    public void test(int c) {
        highOrder(y -> a + y);
        highOrder(y -> b + y);
        highOrder(y -> c + y);
    }
}

package com.itheima.day2.methodref;

public class MethodRef2 {
    public static void main(String[] args) {
        highOrder(Student::hello);
    }

    static void highOrder(Type3 lambda) {
        System.out.println(lambda.transfer(new Student("张三"), "你好"));
    }

    interface Type3 {
        String transfer(Student stu, String message);
    }

    static class Student {
        String name;

        public Student(String name) {
            this.name = name;
        }

        public String hello(String message) {
            return this.name + " say: " + message;
        }
    }
}

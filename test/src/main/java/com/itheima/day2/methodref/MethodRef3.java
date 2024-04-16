package com.itheima.day2.methodref;

import java.util.stream.Stream;

public class MethodRef3 {

    static class Util {
        public boolean isMale(Student stu) {
            return stu.sex().equals("男");
        }
        public String xyz(Student stu){
            return stu.name();
        }
    }

    public static void main(String[] args) {
        Util util = new Util();
        Stream.of(
                new Student("张无忌", "男"),
                new Student("周芷若", "女"),
                new Student("宋青书", "男")
        )
        .filter(util::isMale)
//        .map(stu->stu.name()) // lambda 表达式
//        .map(util::xyz) // 对象::非静态方法
        .map(Student::name) // 类名::非静态方法
        .forEach(System.out::println);
    }
    /*
        (stu) -> util.isMale(stu)
        (stu) -> util.xyz(stu)
     */

    /*
        对象::非静态方法

        System.out::println

        stu -> System.out.println(stu)
     */

    record Student(String name, String sex) {
        public String name() {
            return this.name;
        }
        /*
            Student::name
            stu -> stu.name()
         */

        public String sex() {
            return  this.sex;
        }
    }
}

package com.itheima.day2.methodref;

import java.util.stream.Stream;

public class MethodRef1 {
    public static void main(String[] args) {
        /*
            需求：挑选出所有男性学生
         */
        Stream.of(
                new Student("张无忌", "男"),
                new Student("周芷若", "女"),
                new Student("宋青书", "男")
        )
//        .filter(stu -> stu.sex().equals("男"))     // lambda 表达式方式
//          .filter(MethodRef1::isMale)              // 静态方法引用方式
          .filter(Student::isMale)                   // 非静态方法引用方式
//        .forEach(stu -> System.out.println(stu));  // lambda 表达式方式
//        .forEach(MethodRef1::abc);                 // 静态方法引用方式
          .forEach(Student::print);                  // 静态方法引用方式

        /*
            (Student stu) -> stu.sex().equals("男")
            (Student stu) -> MethodRef1.isMale(stu)
         */
        /*
            (Student stu) -> System.out.println(stu)
            类名::静态方法
            (Student stu) -> MethodRef1.abc(stu);
         */
    }

    public static boolean isMale(Student stu) {
        return stu.sex().equals("男");
    }

    public static void abc(Student stu) {
        System.out.println(stu);
    }

    record Student(String name, String sex) {
        public void print() {
            System.out.println(this);
        }
        /*
            Student::print
            (stu) -> stu.print()
         */

        public boolean isMale() {
            return this.sex.equals("男");
        }
        /*
            Student::isMale
            (stu) -> stu.isMale()
         */
    }
}

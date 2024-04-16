package com.itheima.day2.exercise;

import java.util.List;
import java.util.function.Predicate;

public class Exercise6 {
    record Student(String name, int age) {
        boolean abc() {
            return this.age() >= 18;
        }
    }

    /*
        传入参数时，分别用
            类名::静态方法
            类名::非静态方法
        来表示【学生年龄大于等于18】的条件
     */
    static void highOrder(Predicate<Student> predicate) {
        List<Student> list = List.of(
                new Student("张三", 18),
                new Student("李四", 17),
                new Student("王五", 20)
        );
        for (Student stu : list) {
            if (predicate.test(stu)) {
                System.out.println(stu + "通过测试");
            }
        }
    }

    static boolean ageGreaterOrEquals18(Student student) {
        return student.age() >= 18;
    }

    public static void main(String[] args) {
//        highOrder(Exercise6::ageGreaterOrEquals18);
        highOrder(Student::abc);

        /*
            (Student stu) -> stu.abc()
            (Exercise6 obj, Student student) -> obj.abc(student)
         */
    }
}

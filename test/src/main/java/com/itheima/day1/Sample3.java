package com.itheima.day1;

// 成员方法算不算函数？
public class Sample3 {

    static class Student {
        final String name;

        public Student(String name) {
            this.name = name;
        }

        public String getName(Student this) {
            return this.name;
        }
    }

    public static void main(String[] args) {
        Student s1 = new Student("张三");
        System.out.println(s1.getName()); // getName(s1)
        System.out.println(s1.getName()); // getName(s1)

        Student s2 = new Student("李四");
        System.out.println(s2.getName()); // getName(s2)
        System.out.println(s2.getName()); // getName(s2)

        /*
            方法不算函数
                它只能算是某个对象专用的法则，是小道，还成不了大道
         */
    }
}

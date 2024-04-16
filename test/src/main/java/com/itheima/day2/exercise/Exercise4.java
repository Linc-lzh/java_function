package com.itheima.day2.exercise;

import java.io.File;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Exercise4 {

    record Student(String name) {}

    // 写出与下列 lambda表达式 等价的方法引用
    public static void main(String[] args) {
        Function<String, Integer> lambda1 = Integer::parseInt;
//        Function<String, Integer> lambda = (String s) -> Integer.parseInt(s);

//        BiPredicate<List<String>, String> lambda2 = (list, element) -> list.contains(element);
        BiPredicate<List<String>, String> lambda2 = List::contains;

//        BiPredicate<Student, Object> lambda3 = (stu, obj) -> stu.equals(obj);
        BiPredicate<Student, Object> lambda3 = Student::equals;

//        Predicate<File> lambda4 = (file) -> file.exists();
        Predicate<File> lambda4 = File::exists;

//        Runtime runtime = Runtime.getRuntime();

//        Supplier<Long> lambda5 = () -> runtime.freeMemory();
        Supplier<Long> lambda5 = Runtime.getRuntime()::freeMemory;
    }
}

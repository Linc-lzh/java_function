package com.itheima.day2.exercise;

public class Exercise5 {
    record Color(Integer red, Integer green, Integer blue) { }

    // 如果想用 `Color::new` 来构造 Color 对象，还应当补充哪些代码

    public static void main(String[] args) {
        TernaryFunction lambda = Color::new; // (Integer, Integer, Integer) -> Color

        Color white = lambda.create(255, 255, 255);
        System.out.println(white);
    }

    @FunctionalInterface
    interface TernaryFunction {
        Color create(Integer red, Integer green, Integer blue);
    }
}

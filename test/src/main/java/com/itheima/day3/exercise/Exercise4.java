package com.itheima.day3.exercise;

import java.util.function.IntConsumer;
import java.util.stream.Stream;

public class Exercise4 {
    public static void main(String[] args) {
//        IntStream.rangeClosed(1, 9)
//                .boxed()
//                .flatMap(x -> Stream.concat(
//                        IntStream.rangeClosed(1, x)
//                                .mapToObj(y -> y + "*" + x + "=" + y * x + "\t"),
//                        Stream.of("\n")
//                ))
//                .forEach(System.out::print);


//        Function<Integer, Long> factorial = Exercise3::factorial;

//        Stream.of(10).mapMultiToInt(Exercise4::fibonacci)
//                .forEach(System.out::println);

        fibonacci(3, System.out::println);
    }

    static int[] cache = new int[100];
    static int fibonacci(int n, IntConsumer consumer) {
        if(cache[n] > 0) {
            return cache[n];
        }
        if(n == 1 || n == 2) {
            cache[n] = 1;
            consumer.accept(1);
            return cache[n];
        }
        int r = fibonacci(n - 1, consumer) + fibonacci(n - 2, consumer);
        cache[n] = r;
        consumer.accept(r);
        return r;
    }

    static int factorial(int n, IntConsumer consumer) {
        if(n <= 1) {
            consumer.accept(1);
            return 1;
        }
        int l = n * factorial(n - 1, consumer);
        consumer.accept(l);
        return l;
    }

    static int add(int a, int b) {
        return a + b;
    }

    interface Lambda {
        int op(int a, int b);
    }
}

package com.itheima.day3.performance;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

// 性能：求和
public class T01Sum {
    @State(Scope.Benchmark)
    public static class MyState {
        public static final int COUNT = 10000;

        public int[] numbers = new int[COUNT];
        public List<Integer> numberList = new ArrayList<>(COUNT);

        public MyState() {
            for (int i = 0; i < COUNT; i++) {
                int x = i + 1;
                numbers[i] = x;
                numberList.add(i, x);
            }
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public int primitive(MyState state) {
        int sum = 0;
        for (int number : state.numbers) {
            sum += number;
        }
        return sum;
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public int boxed(MyState state) {
        int sum = 0;
        for (Integer i : state.numberList) {
            sum += i;
        }
        return sum;
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public int stream(MyState state) {
        return state.numberList.stream().reduce(0, (a, b) -> a + b);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public int intStream(MyState state) {
        return IntStream.of(state.numbers).sum();
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(T01Sum.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();

//        MyState state = new MyState();
//        T01Sum test = new T01Sum();
//        System.out.println(test.primitive(state));
//        System.out.println(test.boxed(state));
//        System.out.println(test.stream(state));
//        System.out.println(test.intStream(state));
    }
}

package com.itheima.day3.performance;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import static java.util.stream.Collectors.*;

// 性能：并行(发)收集
// ConcurrentHashMap
// HashMap
public class T03Concurrent {

    static final int n = 1000000;

    @State(Scope.Benchmark)
    public static class MyState {
        int[] numbers = new int[n];

        {
            for (int i = 0; i < n; i++) {
                numbers[i] = ThreadLocalRandom.current().nextInt(n / 10);
            }
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public Map<Integer, Integer> loop1(MyState state) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int number : state.numbers) {
            map.merge(number, 1, Integer::sum);
        }
        return map;
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public Map<Integer, AtomicInteger> loop2(MyState state) {
        Map<Integer, AtomicInteger> map = new HashMap<>();
        for (int number : state.numbers) {
            map.computeIfAbsent(number, k -> new AtomicInteger()).getAndIncrement();
        }
        return map;
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public Map<Integer, Long> sequence(MyState state) {
        return Arrays.stream(state.numbers).boxed()
                .collect(groupingBy(Function.identity(), counting()));
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public Map<Integer, Long> parallelNoConcurrent(MyState state) {
        return Arrays.stream(state.numbers).boxed()
                .parallel()
                .collect(groupingBy(Function.identity(), counting()));
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public ConcurrentMap<Integer, Long> parallelConcurrent(MyState state) {
        return Arrays.stream(state.numbers).boxed()
                .parallel()
                .collect(groupingByConcurrent(Function.identity(), counting()));
    }

    public static void main(String[] args) throws RunnerException, ExecutionException, InterruptedException {
        Options opt = new OptionsBuilder()
                .include(T03Concurrent.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
//        MyState state = new MyState();
//        T03Concurrent test = new T03Concurrent();
//        System.out.println(test.loop1(state));
//        System.out.println(test.loop2(state));
//        System.out.println(test.sequence(state));
//        System.out.println(test.parallelNoConcurrent(state));
//        System.out.println(test.parallelConcurrent(state));
    }
}

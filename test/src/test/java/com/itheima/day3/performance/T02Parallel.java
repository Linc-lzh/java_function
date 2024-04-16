package com.itheima.day3.performance;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.stream.IntStream;

// 性能：求最大值
public class T02Parallel {
    static final int n = 1000000;

    @State(Scope.Benchmark)
    public static class MyState {
        int[] numbers = new int[n];
        {
            for (int i = 0; i < n; i++) {
                numbers[i] = ThreadLocalRandom.current().nextInt(10000000);
            }
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public int primitive(MyState state) {
        int max = 0;
        for (int number : state.numbers) {
            if (number > max) {
                max = number;
            }
        }
        return max;
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public int sequence(MyState state) {
        return IntStream.of(state.numbers).max().orElse(0);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public int parallel(MyState state) {
        return IntStream.of(state.numbers).parallel().max().orElse(0);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public int custom(MyState state) throws ExecutionException, InterruptedException {
        int[] numbers = state.numbers;
        int step = n / 10;
        ArrayList<Future<Integer>> result = new ArrayList<>();
        try (ExecutorService service = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int j = 0; j < n; j += step) {
                int k = j;
                result.add(service.submit(() -> {
                    int max = 0;
                    for (int i = k; i < k + step; i++) {
                        if (numbers[i] > max) {
                            max = numbers[i];
                        }
                    }
                    return max;
                }));
            }
        }

        System.out.println(result.size());
        int max = 0;
        for (Future<Integer> future : result) {
            if (future.get() > max) {
                max = future.get();
            }
        }
        return max;
    }


    public static void main(String[] args) throws RunnerException, ExecutionException, InterruptedException {
        Options opt = new OptionsBuilder()
                .include(T02Parallel.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
//        MyState state = new MyState();
//        T02Parallel test = new T02Parallel();
//        System.out.println(test.primitive(state));
//        System.out.println(test.sequence(state));
//        System.out.println(test.parallel(state));
//        System.out.println(test.custom(state));
    }
}

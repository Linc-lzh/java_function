package jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.BinaryOperator;

public class Sample2 {

    @Benchmark
    public int origin() {
        return add(1, 2);
    }

    static int add(int a, int b) {
        return a + b;
    }

    static Method method;
    static MethodHandle methodHandle;
    static {
        try {
            method = Sample2.class.getDeclaredMethod("add", int.class, int.class);
            methodHandle = MethodHandles.lookup().unreflect(method);
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Benchmark
    public Object reflection() throws InvocationTargetException, IllegalAccessException {
        return method.invoke(null, 1, 2);
    }

    @Benchmark
    public Object method() throws Throwable {
        return methodHandle.invoke(1, 2);
    }

    @Benchmark
    public int lambda() {
        return test((a, b) -> a + b, 1, 2);
    }

    @FunctionalInterface
    interface Add {
        int add(int a, int b);
    }

    static int test(Add add, int a, int b) {
        return add.add(a, b);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(Sample2.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
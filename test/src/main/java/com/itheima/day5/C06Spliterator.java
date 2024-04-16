package com.itheima.day5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

// 流的构建与切分
public class C06Spliterator {
    static Logger logger = LoggerFactory.getLogger("Test");

    public static void main(String[] args) throws IOException {
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};

//        Stream<Integer> s1 = list.stream();
//        Stream<Integer> s2 = Arrays.stream(array);

        Spliterator<Integer> sp1 = list.spliterator();
//        sp1.tryAdvance(System.out::println);
//        sp1.tryAdvance(System.out::println);
//        sp1.tryAdvance(System.out::println);
//        System.out.println("======================");
//        sp1.forEachRemaining(System.out::println);
        Spliterator<Integer> sp2 = sp1.trySplit();
        Spliterator<Integer> sp3 = sp2.trySplit();

//        System.out.println("sp1:");
//        sp1.forEachRemaining(System.out::println);
//        System.out.println("sp2:");
//        sp2.forEachRemaining(System.out::println);
//        System.out.println("sp3:");
//        sp3.forEachRemaining(System.out::println);

        CompletableFuture.supplyAsync(() -> StreamSupport.stream(sp1, false).reduce(0, Integer::sum))
                .thenAccept(x -> logger.info("{}", x));

        CompletableFuture.supplyAsync(() -> StreamSupport.stream(sp2, false).reduce(0, Integer::sum))
                .thenAccept(x -> logger.info("{}", x));

        CompletableFuture.supplyAsync(() -> StreamSupport.stream(sp3, false).reduce(0, Integer::sum))
                .thenAccept(x -> logger.info("{}", x));

        System.in.read();
    }
}

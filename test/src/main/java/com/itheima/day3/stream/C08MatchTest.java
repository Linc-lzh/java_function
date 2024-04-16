package com.itheima.day3.stream;

import java.util.stream.IntStream;

public class C08MatchTest {
    public static void main(String[] args) {
        IntStream stream = IntStream.of(1, 3, 5);

//        System.out.println(stream.anyMatch(x -> (x & 1) == 0));
//        System.out.println(stream.allMatch(x -> (x & 1) == 0));
        System.out.println(stream.noneMatch(x -> (x & 1) == 0));
    }
}

package com.itheima.test;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Test18 {
    public static void main(String[] args) {
        Stream.iterate(new int[]{0,1}, r -> new int[]{r[1], r[0]+r[1]})
                .limit(10)
                .forEach(r->{
                    System.out.println(Arrays.toString(r));
                });


        Iterator iterator = iterator();

        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());


    }

    @FunctionalInterface
    interface Iterator{
        int next();
    }

    public static Iterator iterator() {
        int[] array = {1, 2, 3, 4, 5};
        AtomicInteger i = new AtomicInteger(0);
        return () -> {
            int k = i.getAndIncrement();
            if(k == array.length) {
                throw new RuntimeException("No More Element");
            }
            return array[k];
        };
    }


    /*
        row = 1
        col = 0

        [1][0] [0][1]

        row = 3
        col = 0
        [3][0] [2][1] [1][2] [0][3]


     */
}

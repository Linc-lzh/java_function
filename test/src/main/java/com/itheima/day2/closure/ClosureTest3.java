package com.itheima.day2.closure;

import java.util.concurrent.atomic.AtomicInteger;

public class ClosureTest3 {
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
}

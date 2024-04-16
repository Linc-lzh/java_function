package com.itheima.day5;

import java.util.Arrays;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.StreamSupport;

public class TestSpliterator {
    static class MySpliterator<T> implements Spliterator<T> {
        T[] array;
        int begin;
        int end;

        public MySpliterator(T[] array, int begin, int end) {
            this.array = array;
            this.begin = begin;
            this.end = end;
        }

        @Override
        public boolean tryAdvance(Consumer<? super T> action) {
            if (begin > end) {
                return false;
            }
            action.accept(array[begin++]);
            return true;
        }

        @Override
        public Spliterator<T> trySplit() {
            if (estimateSize() > 4) {
                int mid = (begin + end) >>> 1;
                MySpliterator<T> res = new MySpliterator<>(array, begin, mid);
                System.out.println(simple() + " 切分 " + res);
                begin = mid + 1;
                System.out.println(simple() + " 剩下 " + this);
                return res;
            }
            return null;
        }

        @Override
        public String toString() {
            return Arrays.toString(Arrays.copyOfRange(array, begin, end + 1));
        }

        @Override
        public long estimateSize() {
            return end - begin + 1;
        }

        @Override
        public int characteristics() {
            return Spliterator.SUBSIZED | Spliterator.ORDERED;
        }
    }

    public static void main(String[] args) {
        Integer[] all = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        MySpliterator<Integer> spliterator = new MySpliterator<>(all, 0, all.length - 1);

        StreamSupport.stream(spliterator, true)
                .forEach(x -> System.out.println(simple() + ":" + x));

    }

    private static String simple() {
        String name = Thread.currentThread().getName();
        int idx = name.indexOf("worker");
        if (idx > 0) {
            return name.substring(idx);
        }
        return name;
    }
}

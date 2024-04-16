package com.itheima.ap;

import java.util.Arrays;
import java.util.stream.Collectors;

public class FreeResponse2018_4 {
    static class ArrayTester {
        /**
         * Returns an array containing the elements of column c of arr2D in the same order as
         * they appear in arr2D.
         * Precondition: c is a valid column index in arr2D.
         * Postcondition: arr2D is unchanged.
         */
        public static int[] getColumn(int[][] arr2D, int c) {
            int[] column = new int[arr2D.length];
            for (int i = 0; i < arr2D.length; i++) {
                column[i] = arr2D[i][c];
            }
            return column;
        }

        /**
         * Returns true if and only if every value in arr1 appears in arr2.
         * Precondition: arr1 and arr2 have the same length.
         * Postcondition: arr1 and arr2 are unchanged.
         */
        public static boolean hasAllValues(int[] arr1, int[] arr2) {
            int[] a = Arrays.copyOf(arr1, arr1.length);
            int[] b = Arrays.copyOf(arr2, arr2.length);
            Arrays.sort(a);
            Arrays.sort(b);
            return Arrays.equals(a, b);
        }

        /**
         * Returns true if arr contains any duplicate values;
         * false otherwise.
         */
        public static boolean containsDuplicates(int[] arr) {
            return Arrays.stream(arr).boxed().collect(Collectors.toSet()).size() != arr.length;
        }

        /**
         * Returns true if square is a Latin square as described in part (b);
         * false otherwise.
         * Precondition: square has an equal number of rows and columns.
         * square has at least one row.
         */
        public static boolean isLatin(int[][] square) {
            int[] firstRow = square[0];
            if (containsDuplicates(firstRow)) {
                return false;
            }

            for (int i = 1; i < square.length; i++) {
                if (!hasAllValues(firstRow, square[i])) {
                    return false;
                }
            }

            for (int i = 0; i < square.length; i++) {
                if (!hasAllValues(firstRow, getColumn(square, i))) {
                    return false;
                }
            }
            return true;
        }

        public static void main(String[] args) {
            System.out.println(ArrayTester.isLatin(new int[][]{
                    {1, 2, 3},
                    {2, 3, 1},
                    {3, 1, 2}
            }));

            System.out.println(ArrayTester.isLatin(new int[][]{
                    {10, 30, 20, 0},
                    {0, 20, 30, 10},
                    {30, 0, 10, 20},
                    {20, 10, 0, 30},
            }));

            System.out.println(ArrayTester.isLatin(new int[][]{
                    {1, 2, 1},
                    {2, 1, 1},
                    {1, 1, 2}
            }));

            System.out.println(ArrayTester.isLatin(new int[][]{
                    {1, 2, 3},
                    {3, 1, 2},
                    {7, 8, 9}
            }));

            System.out.println(ArrayTester.isLatin(new int[][]{
                    {1, 2},
                    {1, 2}
            }));


        }
    }
}

package com.itheima.ap;

public class FreeResponse2014_2 {
    static public class Mountain {
        /**
         * @param array an array of positive integer values   *  @param stop the last index to check   *         Precondition: 0 £stop < array.length    *  @return true if for each j such that 0 £j < stop, array[j] < array[j + 1] ;   *         false otherwise
         */
        public static boolean isIncreasing(int[] array, int stop) {  /*  implementation not shown  */
            return true;
        }

        /**
         * @param array an array of positive integer values   *  @param start the first index to check   *        Precondition: 0 £start < array.length - 1    *  @return true if for each j such that start£j < array.length - 1,   *               array[j] > array[j + 1];   *          false otherwise
         */
        public static boolean isDecreasing(int[] array, int start) {
            return true;
        }

        /**
         * @param array an array of positive integer values   *         Precondition: array.length > 0    *  @return the index of the first peak (local maximum) in the array, if it exists;   *          -1 otherwise
         */
        public static int getPeakIndex(int[] array) {
            for (int i = 1; i < array.length - 1; i++) {
                if (array[i - 1] < array[i] && array[i] > array[i + 1]) {
                    return i;
                }
            }
            return -1;
        }

        /**
         * 只有一个峰
         * @param array an array of positive integer values   *         Precondition: array.length > 0    *  @return true if array contains values ordered as a mountain;   *          false otherwise
         */
        public static boolean isMountain(int[] array) {  /*  to be implemented in part (b)  */
            int index = getPeakIndex(array);
            if (index == -1) {
                return false;
            }
            return isIncreasing(array, index) && isDecreasing(array, index);
        }   // There may be instance variables, constructors, and methods that are not shown.
    }
}

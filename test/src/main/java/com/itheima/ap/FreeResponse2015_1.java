package com.itheima.ap;

/*
    大概意思
    a) 完成 arraySum 实现一维数组求和
    b) 完成 rowSums 实现二维数组每一行求和
    c) 完成 isDiverse : 如果二维数组的每一行和都不同返回 true 否则返回 false
 */
public class FreeResponse2015_1 {
    public static int arraySum(int[] arr) {
        int sum = 0;
        for (int value : arr) {
            sum += value;
        }
        return sum;
    }

    public static int[] rowSums(int[][] arr2D) {
        int[] result = new int[arr2D.length];
        for (int i = 0; i < arr2D.length; i++) {
            result[i] = arraySum(arr2D[i]);
        }
        return result;
    }

    public static boolean isDiverse(int[][] arr2D) {
        int[] result = rowSums(arr2D);
        for (int i = 0; i < result.length; i++) {
            for (int j = i + 1; j < result.length; j++) {
                if (result[i] == result[j]) {
                    return false;
                }
            }
        }
        return true;
    }
}

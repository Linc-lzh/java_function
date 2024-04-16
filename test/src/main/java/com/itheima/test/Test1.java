package com.itheima.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Test1 {
    /*
    用户购买机票时，机票原价会按照淡季、旺季，头等舱还是经济舱的情况进行相应的优惠，优惠方案
    如下:5-10月为旺季，头等舱9折，经济舱8.5折; 11月到来年4月为淡季，头等舱7折，经济舱
    6.5折，请开发程序计算出用户当前机票的优惠价
     */


    public static double discount2(double original, Function<Integer, double[]> rule, int month, Cabin cabin) {
        double[] t = rule.apply(month);
        return (cabin == Cabin.FirstClass ? t[0] : t[1]) * original;
    }

    static double[] seasonDiscount(int month) {
        return switch (month) {
            case 5, 6, 7, 8, 9, 10 -> new double[]{0.9, 0.85};
            default -> new double[]{0.7, 0.65};
        };
    }

    public enum Cabin {
        FirstClass, EconomyClass
    }

    private final static Map<Integer, Map<Cabin, Double>> TABLE = new HashMap<>();

    static {
        Map<Cabin, Double> peakSeason = Map.of(
                Cabin.FirstClass, 0.9,
                Cabin.EconomyClass, 0.85
        );

        Map<Cabin, Double> offPeakSeason = Map.of(
                Cabin.FirstClass, 0.7,
                Cabin.EconomyClass, 0.65
        );

        for (int i = 1; i <= 12; i++) {
            if (i >= 5 && i <= 10) {
                TABLE.put(i, peakSeason);
            } else {
                TABLE.put(i, offPeakSeason);
            }
        }
    }

    public static double discount(double original, int month, Cabin cabin) {
        return original * TABLE.get(month).get(cabin);
    }


    public static void main(String[] args) {
        System.out.println(discount(1000, 10, Cabin.EconomyClass));
        System.out.println(discount2(1000, Test1::seasonDiscount, 10, Cabin.EconomyClass));
        System.out.println(discount(1000, 1, Cabin.FirstClass));
        System.out.println(discount2(1000, Test1::seasonDiscount, 1, Cabin.FirstClass));
        System.out.println(discount2(1000, month -> new double[]{1.0, 0.5}, 1, Cabin.FirstClass));
    }


    public static double discount3(double original, int month, String cabin) {
        if (month >= 5 && month <= 10) {
            return (cabin.equals("头等舱") ? 0.9 : 0.85) * original;
        } else {
            return (cabin.equals("头等舱") ? 0.7 : 0.65) * original;
        }
    }

    /*

     */
    static double[][] table = {
            {0.7, 0.65},
            {0.7, 0.65},
            {0.7, 0.65},
            {0.7, 0.65},
            {0.9, 0.85},
            {0.9, 0.85},
            {0.9, 0.85},
            {0.9, 0.85},
            {0.9, 0.85},
            {0.9, 0.85},
            {0.7, 0.65},
            {0.7, 0.65}
    };

    public static double discount4(double original, int month, String cabin) {
        return table[month][cabin.equals("头等舱") ? 0 : 1] * original;
    }

}

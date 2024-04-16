package com.itheima.ap;

import java.util.ArrayList;

public class FreeResponse2023_3 {
    static class WeatherData {
        private ArrayList<Double> temperatures;

        public void cleanData(double lower, double upper) {
            for (int i = 0; i < temperatures.size(); i++) {
                double t = temperatures.get(i);
                if (t < lower || t > upper) {
                    temperatures.remove(i);
                    i--;
                }
            }
        }

        public int longestHeatWave(double threshold) {
            int length = 0;
            int maxLength = 0;
            for (double t : temperatures) {
                if (t > threshold) {
                    length++;
                    if (length > maxLength) {
                        maxLength = length;
                    }
                } else {
                    length = 0;
                }
            }
            return maxLength;
        }
    }
}

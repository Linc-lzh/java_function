package com.itheima.ap;

public class FreeResponse2018_1 {
    static class FrogSimulation {
        /**
         * 目标距离
         */
        private int goalDistance;
        /**
         * 最大跳跃次数
         */
        private int maxHops;

        public FrogSimulation(int dist, int numHops) {
            goalDistance = dist;
            maxHops = numHops;
        }

        /**
         * 一次跳跃的距离，不用你实现
         */
        private int hopDistance() {
            return -1;
        }

        /**
         * Simulates a frog attempting to reach the goal as described in part (a).
         * Returns true if the frog successfully reached or passed the goal during the simulation;
         * false otherwise.
         */
        public boolean simulate() {
            int sum = 0;
            for (int i = 0; i < maxHops; i++) {
                sum += hopDistance();
                if (sum >= goalDistance) {
                    return true;
                }
                if (sum < 0) {
                    return false;
                }
            }
            return false;
        }

        /**
         * Runs num simulations and returns the proportion of simulations in which the frog
         * successfully reached or passed the goal.
         * Precondition: num > 0
         */
        public double runSimulations(int num) {
            double count = 0;
            for (int i = 0; i < num; i++) {
                if (simulate()) {
                    count++;
                }
            }
            return count / num;
        }
    }
}

package com.itheima.ap;

public class FreeResponse2022_1 {
    static class Level {
        public boolean goalReached() {
            return true;
        }

        public int getPoints() {
            return 100;
        }
    }

    static class Game {
        private Level levelOne;
        private Level levelTwo;
        private Level levelThree;

        public boolean isBonus() {
            return true; // 可能是 false
        }

        public int getScore() {
            int total = 0;
            if (levelOne.goalReached()) {
                total += levelOne.getPoints();
                if (levelTwo.goalReached()) {
                    total += levelTwo.getPoints();
                    if (levelThree.goalReached()) {
                        total += levelThree.getPoints();
                    }
                }
            }
            return isBonus() ? total * 3 : total;
        }

        public void play() {

        }

        public int playManyTimes(int num) {
            int max = 0;

            for (int i = 0; i < num; i++) {
                play();
                int score = getScore();
                if (score > max) {
                    max = score;
                }
            }

            return max;
        }
    }
}

package com.itheima.ap;

import java.util.ArrayList;

public class FreeResponse2014_4 {
    static class ScoreInfo {
        private int score;
        private int numStudents;

        public ScoreInfo(int aScore) {
            score = aScore;
            numStudents = 1;
        }

        /**
         * adds 1 to the number of students who earned this score
         */
        public void increment() {
            numStudents++;
        }

        /**
         * @return this score
         */
        public int getScore() {
            return score;
        }

        /**
         * @return the number of students who earned this score
         */
        public int getFrequency() {
            return numStudents;
        }
    }

    static class Stats {
        private ArrayList<ScoreInfo> scoreList;     // listed in increasing score order; no two ScoreInfo objects contain the same score  /** Records a score in the database, keeping the database in increasing score order. If no other   *  ScoreInfo object represents score,a new ScoreInfo object representing score     *  is added to the database; otherwise, the frequency in the ScoreInfo object representing   *  score is incremented.    *  @param score a score to be recorded in the list   *  @return true if a new ScoreInfo object representing score was added to the list;    *          false otherwise   */

        public boolean record(int score) {
            for (int i = 0; i < scoreList.size(); i++) {
                int s = scoreList.get(i).getScore();
                if (s == score) {
                    scoreList.get(i).increment();
                    return true;
                } else if(score < s) {
                    scoreList.add(i, new ScoreInfo(score));
                    return false;
                }
            }
            scoreList.add(new ScoreInfo(score));
            return false;
        }

        /**
         * Records all scores in stuScores in the database, keeping the database in increasing score order   *  @param stuScores an array of student test scores
         */
        public void recordScores(int[] stuScores) {
            for (int stuScore : stuScores) {
                record(stuScore);
            }
        }   // There may be instance variables, constructors, and methods that are not shown.
    }
}

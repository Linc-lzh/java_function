package com.itheima.ap;

import java.util.ArrayList;

public class FreeResponse2018_2 {
    static class WordPair {

        private String first;
        private String second;

        /**
         * Constructs a WordPair object.
         */
        public WordPair(String first, String second) {
            this.first = first;
            this.second = second;
        }

        /**
         * Returns the first string of this WordPair object.
         */
        public String getFirst() {
            return this.first;
        }

        /**
         * Returns the second string of this WordPair object.
         */
        public String getSecond() {
            return this.second;
        }

        @Override
        public String toString() {
            return "[" + first + ',' + second + ']';
        }
    }

    static class WordPairList {
        /**
         * The list of word pairs, initialized by the constructor.
         */
        private ArrayList<WordPair> allPairs;

        /**
         * Constructs a WordPairList object as described in part (a).
         * Precondition: words.length >= 2
         */
        public WordPairList(String[] words) {
            allPairs = new ArrayList<>();
            for (int i = 0; i < words.length; i++) {
                for (int j = i + 1; j < words.length; j++) {
                    allPairs.add(new WordPair(words[i], words[j]));
                }
            }
        }

        /**
         * Returns the number of matches as described in part (b).
         */
        public int numMatches() {
            int count = 0;
            for (WordPair allPair : allPairs) {
                if (allPair.getFirst().equals(allPair.getSecond())) {
                    count++;
                }
            }
            return count;
        }

        public static void main(String[] args) {
            WordPairList wordPairList = new WordPairList(new String[]{"the", "red", "fox", "the", "red"});
            System.out.println(wordPairList.allPairs);
        }
    }
}

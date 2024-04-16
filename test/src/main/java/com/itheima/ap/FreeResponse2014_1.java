package com.itheima.ap;

import java.util.List;

public class FreeResponse2014_1 {
    /*static String scrambleWord(String word) {
        int length = word.length();
        String r = "";
        int i = 0;
        while (i < length - 1) {
            String curr = word.substring(i, i + 1);
            String next = word.substring(i + 1, i + 2);
            if (curr.equals("A") && !next.equals("A")) {
                r += (next + curr);
                i += 2;
            } else {
                r += curr;
                i++;
            }
        }
        if (i < word.length()) {
            r += word.substring(i);
        }
        return r;
    }

    static void scrambleOrRemove(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            String word = list.get(i);
            String scramble = scrambleWord(word);
            if (scramble.equals(word)) {
                list.remove(i);
                i--;
            } else {
                list.set(i, scramble);
            }
        }
    }*/

    public class WordScrambler {
        private String[] scrambledWords;

        /**
         * @param wordArr an array of String objects   *         Precondition: wordArr.length is even
         */
        public WordScrambler(String[] wordArr) {
            scrambledWords = mixedWords(wordArr);
        }

        /**
         * @param word1 a String of characters   *  @param word2 a String of characters   *  @return a String that contains the first half of word1 and the second half of word2
         */
        private String recombine(String word1, String word2) {
            return word1.substring(0, word1.length() / 2)
                    + word2.substring(word2.length() / 2);
        }

        /**
         * @param words an array of String objects   *         Precondition: words.length is even   *  @return an array of String objects created by recombining pairs of strings in array words    *  Postcondition: the length of the returned array is words.length
         */
        private String[] mixedWords(String[] words) {
            String[] result = new String[words.length];
            for (int i = 0; i < words.length; i+=2) {
                result[i] = recombine(words[i], words[i + 1]);
                result[i+1] = recombine(words[i+1], words[i]);
            }
            return result;
        }   // There may be instance variables, constructors, and methods that are not shown.
    }
}

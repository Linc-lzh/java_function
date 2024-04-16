package com.itheima.ap;

public class FreeResponse2018_3 {
    interface StringChecker {
        /**
         * Returns true if str is valid.
         */
        boolean isValid(String str);
    }

    static class CodeWordChecker implements StringChecker {
        private int min;
        private int max;
        private String notOccur;

        public CodeWordChecker(String notOccur) {
            this(6, 20, notOccur);
        }

        public CodeWordChecker(int min, int max, String notOccur) {
            this.min = min;
            this.max = max;
            this.notOccur = notOccur;
        }

        @Override
        public boolean isValid(String str) {
            int length = str.length();
            if (length < min || length > max) {
                return false;
            }
            if (str.contains(notOccur)) {
                return false;
            }
            return true;
        }
    }
}

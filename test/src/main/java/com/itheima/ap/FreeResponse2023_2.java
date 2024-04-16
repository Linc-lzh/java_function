package com.itheima.ap;

public class FreeResponse2023_2 {
    static class Sign {
        private String text;
        private int width;

        public Sign(String text, int width) {
            this.text = text;
            this.width = width;
        }

        public int numberOfLines() {
            int length = text.length();
            if (length % width == 0) {
                return length / width;
            } else {
                return length / width + 1;
            }
        }

        public String getLines() {
            int lines = numberOfLines();
            if (lines == 0) {
                return null;
            }
            String result = "";
            for (int i = 0; i < lines - 1; i++) {
                result += text.substring(i * width, (i + 1) * width) + ";";
            }
            return result + text.substring((lines - 1) * width);
        }
    }

    public static void main(String[] args) {

    }
}

package com.itheima.ap;

public class FreeResponse2023_4 {
    static class Candy {
        public String getFlavor() {
            return "";
        }
    }
    static class BoxOfCandy {
        private Candy[][] box;

        public boolean moveCandyToFirstRow(int col) {
            if (box[0][col] != null) {
                return true;
            }
            for (int row = 1; row < box.length; row++) {
                if (box[row][col] != null) {
                    box[0][col] = box[row][col];
                    box[row][col] = null;
                    return true;
                }
            }
            return false;
        }

        public Candy removeNextByFlavor(String flavor) {
            for (int row = box.length-1; row >= 0; row--) {
                for (int col = 0; col < box[0].length; col++) {
                    if (flavor.equals(box[row][col].getFlavor())) {
                        Candy removed = box[row][col];
                        box[row][col] = null;
                        return removed;
                    }
                }
            }
            return null;
        }
    }
}

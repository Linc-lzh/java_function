package com.itheima.ap;

public class FreeResponse2022_4 {

    static class Data {
        public static final int MAX = 1000;
        private int[][] grid;

        public void repopulate() {
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[0].length; col++) {
                    int rval = (int) (Math.random() * MAX) + 1;
                    while (rval % 10 != 0 || rval % 100 == 0) {
                        rval = (int) (Math.random() * MAX) + 1;
                    }
                    grid[row][col] = rval;
                }
            }
        }

        public int countIncreasingCols() {
            int count = 0;
            for (int col = 0; col < grid[0].length; col++) {
                boolean increased = true;
                for (int row = 1; row < grid.length; row++) {
                    if (grid[row][col] < grid[row - 1][col]) {
                        increased = false;
                        break;
                    }
                }
                if (increased) {
                    count++;
                }
            }
            return count;
        }
    }

}

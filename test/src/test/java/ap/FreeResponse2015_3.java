package ap;

import java.util.ArrayList;
import java.util.List;

/*
    用 SparseArray 表示稀疏矩阵
    用 SparseArrayEntry 表示稀疏矩阵中一项

    a) 实现 getValueAt 方法根据 row 和 col 返回值
    b) 实现 removeColumn 删除某一列
        比较难一些:
            要会 List 循环时删除
            并且要注意删除列之后, 此列之后的 entry 要修改 col 为 col-1
 */
public class FreeResponse2015_3 {
    static class SparseArrayEntry {
        /**
         * The row index and column index for this entry in the sparse array
         */
        private int row;
        private int col;
        /**
         * The value of this entry in the sparse array
         */
        private int value;

        /**
         * Constructs a SparseArrayEntry object that represents a sparse array element    *  with row index r and column index c,containing value v.
         */
        public SparseArrayEntry(int r, int c, int v) {
            row = r;
            col = c;
            value = v;
        }

        /**
         * Returns the row index of this sparse array element.
         */
        public int getRow() {
            return row;
        }

        /**
         * Returns the column index of this sparse array element.
         */
        public int getCol() {
            return col;
        }

        /**
         * Returns the value of this sparse array element.
         */
        public int getValue() {
            return value;
        }
    }


    static public class SparseArray {
        /**
         * The number of rows and columns in the sparse array.
         */
        private int numRows;
        private int numCols;
        /**
         * The list of entries representing the non-zero elements of the sparse array. Entries are stored in the   *  list in no particular order. Each non-zero element is represented by exactly one entry in the list.
         */
        private List<SparseArrayEntry> entries;

        /**
         * Constructs an empty SparseArray.
         */
        public SparseArray() {
            entries = new ArrayList<SparseArrayEntry>();
        }

        /**
         * Returns the number of rows in the sparse array.
         */
        public int getNumRows() {
            return numRows;
        }

        /**
         * Returns the number of columns in the sparse array.
         */
        public int getNumCols() {
            return numCols;
        }

        /**
         * Returns the value of the element at row index row and column index col in the sparse array.   *  Precondition:  0 ≤ row < getNumRows()    *             0 ≤ col < getNumCols()
         */
        public int getValueAt(int row, int col) {
            // 遍历集合
            for (SparseArrayEntry entry : entries) {
                // 如果 entry 的行、列与要查找的 行、列 相同，返回 value
                if (entry.getRow() == row && entry.getCol() == col) {
                    return entry.getValue();
                }
            }
            // 走到这，说明没有找到，返回 0
            return 0;
        }

        /**
         * Removes the column col from the sparse array.    *  Precondition:  0 ≤ col < getNumCols()
         */
        public void removeColumn(int col) {
            for (int i = 0; i < entries.size(); i++) {
                SparseArrayEntry entry = entries.get(i);
                if (entry.getCol() == col) {
                    entries.remove(i);
                    i--;
                } else if (entry.getCol() > col) {
                    SparseArrayEntry newEntry = new SparseArrayEntry(entry.getRow(), entry.getCol() - 1, entry.getValue());
                    entries.set(i, newEntry);
                }
            }
            // 这个讲解时漏了，因为删除了一列，所以列总数 numCols 要减一
            this.numCols--;
        }
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("c");
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals("c")) {
                list.remove(i);
                i--;
            }
        }
        System.out.println(list);
    }
}

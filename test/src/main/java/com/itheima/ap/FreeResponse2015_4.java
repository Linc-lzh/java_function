package com.itheima.ap;

import java.util.List;

/*
    a) 实现接口
    b) 实现 Range 实现类
    c) 实现 MultipleGroups 的 contains 方法
 */
public class FreeResponse2015_4 {

    interface NumberGroup {
        boolean contains(int number);
    }

    static class Range implements NumberGroup {

        private int min;
        private int max;

        public Range(int min, int max) {
            this.min = min;
            this.max = max;
        }

        @Override
        public boolean contains(int number) {
            if (number >= min && number <= max) {
                return true;
            }
            return false;
        }
    }

    static class MultipleGroups {
        private List<NumberGroup> groupList;

        public boolean contains(int number) {
            for (NumberGroup group : groupList) {
                if(group.contains(number)) {
                    return true;
                }
            }
            return false;
        }
    }
}

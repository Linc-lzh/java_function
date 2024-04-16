package ap;

import java.util.List;

/*
    a) 实现接口
    b) 实现 Range 实现类
    c) 实现 MultipleGroups 的 contains 方法
 */
public class FreeResponse2015_4 {

    interface NumberGroup {
        // 接口中都是抽象方法（只有方法参数、返回值说明，没有方法体）
        boolean contains(int number);
    }

    // 代表一个范围
    static class Range implements NumberGroup {
        private int min;
        private int max;

        public Range(int min, int max) {
            this.min = min;
            this.max = max;
        }

        // 实现了接口的类要 Override 抽象方法，给出方法具体逻辑
        public boolean contains(int number) {
            if (number >= min && number <= max) {
                return true;
            } else {
                return false;
            }
        }

    }

    static class MultipleGroups {
        private List<NumberGroup> groupList;

        public boolean contains(int number) {
            for (NumberGroup group : groupList) {
                return group.contains(number);
            }
            return false;
        }
    }
}

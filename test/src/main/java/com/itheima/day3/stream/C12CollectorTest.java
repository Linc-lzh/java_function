package com.itheima.day3.stream;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Collector 收集器
public class C12CollectorTest {
    record Hero(String name, int strength) {

    }

    public static void main(String[] args) {
        Stream<String> stream = Stream.of("令狐冲", "风清扬", "独孤求败", "方证",
                "东方不败", "冲虚", "向问天", "任我行", "不戒");
        /*
            1) 收集到 List
            List<String> result = stream.collect(() -> new ArrayList<>(), (list, x) -> list.add(x), (a, b) -> { });
            ArrayList::new   ()->new ArrayList()
            ArrayList::add   (list,x)->list.add(x)
            List<String> result = stream.collect(ArrayList::new, ArrayList::add, (a, b) -> { });
         */
//        List<String> result = stream.collect(Collectors.toList());

        /*
            2) 收集到 Set
            Set<String> result = stream.collect(LinkedHashSet::new, Set::add, (a, b) -> { });
         */
//        Set<String> result = stream.collect(Collectors.toSet());

        /*
            3)收集到 StringBuilder
            StringBuilder sb = stream.collect(StringBuilder::new, StringBuilder::append, (a,b)->{});
         */
//        String result = stream.collect(Collectors.joining());

        /*
            4)收集到 StringJoiner
            StringJoiner sb = stream.collect(()->new StringJoiner(","), StringJoiner::add, (a,b)->{});
         */
//        String result = stream.collect(Collectors.joining(","));

        /*
            3)收集到 Map
            Map<String, Integer> result = stream.collect(HashMap::new, (map,x)->map.put(x, 1), (a, b) -> { });
         */
//        Map<String, Integer> map = stream.collect(Collectors.toMap(x -> x, x -> 1));

        /*
            Map
            3: new ArrayList(["令狐冲","风清扬","向问天","任我行"])
            4: new ArrayList(["独孤求败","东方不败"])
            2: new ArrayList(["方证","冲虚","不戒"])

            下游收集器
         */

//        Map<Integer, List<String>> result = stream.collect(Collectors.groupingBy(x -> x.length(), Collectors.toList()));

        Map<Integer, String> result = stream.collect(Collectors.groupingBy(x -> x.length(), Collectors.joining(",")));
        for (Map.Entry<Integer, String> e : result.entrySet()) {
            System.out.println(e);
        }
    }
}

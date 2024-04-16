package com.itheima.day4.asynchronous;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class C02ExecutorTest {
    static final int INDEX = 0;
    static final int TIME = 1;
    static final int ORDER_ID = 2;
    static final int PRODUCT_ID = 3;
    static final int CATEGORY_ID = 4;
    static final int CATEGORY_CODE = 5;
    static final int BRAND = 6;
    static final int PRICE = 7;
    static final int USER_ID = 8;
    static final int USER_AGE = 9;
    static final int USER_SEX = 10;
    static final int USER_REGION = 11;
    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");

    static Logger logger = LoggerFactory.getLogger("Test");

    /*
        同步：没有执行完之前，后续代码不能执行
        异步：后续代码的执行，并不受异步操作的干扰

        要让一段逻辑异步：
           1. 需要有独立线程
           2. 逻辑需要封装至函数对象，才能让此逻辑不是立刻执行，而是在新线程中的未来某刻执行
     */
    public static void main(String[] args) {
//        logger.info("开始统计");
//        monthlySalesReport(); // 同步操作
//        new Thread(()->monthlySalesReport()).start(); // 异步操作
//        logger.info("执行其它操作");
        try (ExecutorService service = Executors.newFixedThreadPool(3)) {
            logger.info("开始统计");
            /*
                目标：将处理结果的逻辑放在 monthlySalesReport 之外
                做法2：将处理结果的逻辑作为函数对象传递给方法
             */
            service.submit(() -> {
                monthlySalesReport((map)->{
                    /*for (Map.Entry<YearMonth, Long> e : map.entrySet()) {
                        logger.info(e.toString());
                    }*/
                    String string = map.entrySet().stream()
                            .map(e -> e.toString()).collect(Collectors.joining("\n"));
                    try {
                        Files.writeString(Path.of("./result.txt"), string);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            });
            logger.info("执行其它操作");
        }
    }

    private static void monthlySalesReport(Consumer<Map<YearMonth, Long>> consumer) {
        try (Stream<String> lines = Files.lines(Path.of("./data.txt"))) {
            Map<YearMonth, Long> map = lines.skip(1)
                    .map(line -> line.split(","))
                    .collect(groupingBy(array -> YearMonth.from(formatter.parse(array[TIME])), TreeMap::new, counting()));
            consumer.accept(map);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

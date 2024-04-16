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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class C04CompletableFutureTest {
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

    // 1. 显式使用了线程池
    // 2. 函数对象嵌套使用，可读性差
    public static void main(String[] args) throws InterruptedException, IOException {
        // 改进前
        /*try (ExecutorService service = Executors.newFixedThreadPool(3)) {
            logger.info("开始统计");
            service.submit(() -> {
                monthlySalesReport(map -> {
                    for (Map.Entry<YearMonth, Long> e : map.entrySet()) {
                        logger.info(e.toString());
                    }
                });
            });
            logger.info("执行其它操作");
        }*/
        // 改进后
        logger.info("开始统计");
        CompletableFuture
                .supplyAsync(()->monthlySalesReport())
                .thenAccept(map-> {
                    for (Map.Entry<YearMonth, Long> e : map.entrySet()) {
                        logger.info(e.toString());
                    }
                });
        logger.info("执行其它操作");
        System.in.read();
    }

    private static Map<YearMonth, Long> monthlySalesReport() {
        try (Stream<String> lines = Files.lines(Path.of("./data.txt"))) {
            Map<YearMonth, Long> map = lines.skip(1)
                    .map(line -> line.split(","))
                    .collect(groupingBy(array -> YearMonth.from(formatter.parse(array[TIME])), TreeMap::new, counting()));
            return map;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

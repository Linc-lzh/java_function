package com.itheima.day4.asynchronous;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;


public class C03CompletableFutureTest {
    static Logger logger = LoggerFactory.getLogger("Test");

    public static void main(String[] args) throws IOException {
        // 1) 异步执行任务
//        CompletableFuture.runAsync()        在任务不需要返回结果时
//        CompletableFuture.supplyAsync()     在任务需要处理结果时

        CompletableFuture.runAsync(() -> logger.info("异步操作1"));
        CompletableFuture.supplyAsync(() -> {
                    logger.info("异步操作2");
                    return "结果";
                })
                .thenApply(r -> r + "转换后")
                .thenAccept(r -> logger.info(r));

        System.in.read(); // 不让主线程立刻结束

        // 2) 处理异步任务的结果
        /*
            thenApply(Function)         转换结果
            thenApplyAsync              异步转换结果
            thenAccept(Consumer)        消费结果
            thenAcceptAsync(Consumer)   异步消费结果
         */
    }
}

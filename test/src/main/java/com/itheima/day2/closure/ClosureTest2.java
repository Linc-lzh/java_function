package com.itheima.day2.closure;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClosureTest2 {

    // 闭包作用：给函数对象提供参数以外的数据
    public static void main(String[] args) throws IOException {
        // 创建 10 个任务对象，并且每个任务对象给一个任务编号
        List<Runnable> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int k = i + 1;
            Runnable task = () -> System.out.println(Thread.currentThread()+":执行任务" + k);
            list.add(task);
        }

        ExecutorService service = Executors.newVirtualThreadPerTaskExecutor();
        for (Runnable task : list) {
            service.submit(task);
        }
        System.in.read();
    }
}

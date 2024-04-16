package com.itheima.test;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;

// -XX:PretenureSizeThreshold=1048576 -Xlog:gc* -Xmn1024m -Xms4096m -Xmx4096m -XX:+UseG1GC
public class TestGC {
    static final int _1M = 1024 * 1024;

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("------------------------1");
        for (MemoryPoolMXBean bean : ManagementFactory.getMemoryPoolMXBeans()) {
            String name = bean.getName();
//            if (name.equals("Tenured Gen") || name.equals("Eden Space") || name.equals("Survivor Space"))
                System.out.println(name + "  总量:" + toMB(bean.getUsage().getCommitted()) + "M 使用的内存:" + toMB(bean.getUsage().getUsed()) + "M");
        }
        System.out.println("------------------------2");
        Thread.sleep(1000);
        byte[] array = new byte[16 * _1M];
        for (MemoryPoolMXBean bean : ManagementFactory.getMemoryPoolMXBeans()) {
            String name = bean.getName();
//            if (name.equals("Tenured Gen") || name.equals("Eden Space") || name.equals("Survivor Space"))
                System.out.println(name + "  总量:" + toMB(bean.getUsage().getCommitted()) + "M 使用的内存:" + toMB(bean.getUsage().getUsed()) + "M");
        }
        System.out.println(array);
//        System.in.read();
    }

    static long toKB(long bytes) {
        return bytes / 1024L;
    }

    static long toMB(long bytes) {
        return bytes / 1024L / 1024L;
    }
}

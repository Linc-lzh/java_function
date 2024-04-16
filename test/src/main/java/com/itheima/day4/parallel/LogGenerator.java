package com.itheima.day4.parallel;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class LogGenerator {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            generate(i);
        }
    }

    private static void generate(int x) {
        // 设置日志文件名
        String logFileName = String.format("web_server_access_%d.log", x);

        // 创建文件写入器
        try (FileWriter fileWriter = new FileWriter(logFileName, true)) {
            // 设置日期格式
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            // 模拟生成10000条日志记录
            for (int i = 0; i < 100000; i++) {
                // 随机生成客户端IP地址
                String ipAddress = getRandomIpAddress();

                // 随机生成访问时间（假设为程序运行的过去一年内）
                Date date = getRandomDate(new Date(System.currentTimeMillis() - 365 * 24 * 60 * 60 * 1000), new Date());

                // 随机生成请求的URL
                String url = getRandomUrl();

                // 随机生成HTTP状态码
                int httpStatusCode = getRandomHttpStatusCode();

                // 构建日志字符串
                String logEntry = String.format("%s - [%s] %s %d\n",
                        ipAddress, dateFormat.format(date), url, httpStatusCode);

                // 将日志记录写入文件
                fileWriter.write(logEntry);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 随机生成客户端IP地址
    private static String getRandomIpAddress() {
        String[] parts = new String[4];
        for (int i = 0; i < 4; i++) {
            parts[i] = String.valueOf(255 - new Random().nextInt(256));
        }
        return String.join(".", parts);
    }

    // 随机生成访问时间
    private static Date getRandomDate(Date start, Date end) {
        long startMillis = start.getTime();
        long endMillis = end.getTime();
        long randomMillis = startMillis + new Random().nextInt((int)(endMillis - startMillis));
        return new Date(randomMillis);
    }

    // 随机生成请求的URL
    private static String getRandomUrl() {
        String[] resources = {
                "/home", "/about", "/services", "/contact", "/products", "/login",
                "/product1", "/product2", "/product3", "/product4", "/product5",
                "/product6", "/product7", "/product8", "/product9", "/product10",
                "/product11", "/product12", "/product13", "/product14", "/product15",
                "/product16", "/product17", "/product18", "/product19", "/product20",
        };
        return String.join("/", resources[new Random().nextInt(resources.length)]);
    }

    // 随机生成HTTP状态码
    private static int getRandomHttpStatusCode() {
        return new Random().nextInt(6) + 200; // 随机生成2xx或3xx状态码
    }
}
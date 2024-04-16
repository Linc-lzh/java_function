package com.itheima.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatProtocolHandlerCustomizer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executors;

@SpringBootApplication
public class MyApp {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MyApp.class, args);
    }

    private String name;

    @Bean
    public TomcatProtocolHandlerCustomizer<?> tomcatProtocolHandlerCustomizer() {
        return protocolHandler -> {
            System.out.println(this.name);
            protocolHandler.setExecutor(Executors.newVirtualThreadPerTaskExecutor());
        };
    }

    @RestController
    static class MyController {
        @GetMapping("/")
        public String hello() {
            System.out.println(Thread.currentThread());
            return "hello";
        }
    }
}

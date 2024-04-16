package com.itheima.day4.framework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// ApplicationListener
@SpringBootApplication
public class C02ApplicationListener {
    public static class MyEvent extends ApplicationEvent {
        public MyEvent(Object source) {
            super(source);
        }
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(C02ApplicationListener.class, args);
        context.publishEvent(new MyEvent("容器启动"));
        // event->void
    }

    @Bean
    public static ApplicationListener<MyEvent> listener() {
        return event -> System.out.println(event);
    }

    @RestController
    static class MyController {
        @Autowired
        private ApplicationContext context;

        @GetMapping("/hello")
        public String hello() {
            context.publishEvent(new MyEvent("/hello 被访问"));
            return "hello";
        }
    }
}
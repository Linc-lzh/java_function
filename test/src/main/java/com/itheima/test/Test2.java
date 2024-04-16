package com.itheima.test;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

public class Test2 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.registerBean("myBean", MyBean.class, () -> new MyBean(), bd -> bd.setLazyInit(true));
        context.refresh();
        System.out.println("refresh...");
        System.out.println(context.getBean(MyBean.class));

        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

        map.put("a", null);
        map.entrySet().remove(Map.entry("b", "dd"));
        System.out.println(map);
    }

    static class Super {
        public Super a(int a) throws IllegalArgumentException {
            return null;
        }
    }

    static class Sub extends Super {
        @Override
        public Super a(int a) throws RuntimeException {
            return null;
        }
    }
}

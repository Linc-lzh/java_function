package com.itheima.test;

import java.lang.reflect.InvocationTargetException;

public class TestGeneric {
    public static void main(String[] args) {

    }

    public <T> T add(Class<T> cls) {
        try {
            return cls.getConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Double add(Double a, Double b) {
        return a + b;
    }
}

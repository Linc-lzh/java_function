package com.itheima.test;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class Test3 {
    public static void main(String[] args) throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle mh = lookup.findVirtual(A.class, "foo", MethodType.methodType(void.class));
        mh.invoke(new B());


    }

    static class A {
        void foo() {
            System.out.println(this.getClass() + " foo");
        }
    }

    static class B {
        void foo() {
            System.out.println(this.getClass() + " bar");
        }
    }
}

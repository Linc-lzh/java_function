package com.itheima.day5;

import java.lang.invoke.*;
import java.lang.reflect.Method;
import java.util.function.BinaryOperator;

// -Djdk.invoke.LambdaMetafactory.dumpProxyClassFiles
public class C01Lambda1 {
    public static void main(String[] args) throws Throwable {
        BinaryOperator<Integer> lambda = (a, b) -> a + b;
        System.in.read();

        /*
            lambda 表达式是一种语法糖，它仍然会被翻译成 类、对象、方法
            1. 方法从哪来 : 编译器发现代码中出现了 lambda，就会在当前类中生成 private static 方法，方法内包含的就是 lambda 的逻辑
                实验代码
                for (Method method : C01Lambda1.class.getDeclaredMethods()) {
                    System.out.println(method);
                }
            2. 类和对象从哪来 : 运行期间动态生成

            3. MethodHandle 铺垫
                MethodHandle 的执行权限与上下文相关
                    原本有权限调用的方法，正常能调用，通过 MethodHandle 反射也能调用
                    原本没权限调用的方法，正常不能调用，MethodHandle 反射也调用不了

                a) 反射调用静态方法
                MethodHandle mh = MethodHandles.lookup().findStatic(C01Lambda1.class, "lambda$main$2",
                    MethodType.methodType(Integer.class, Integer.class, Integer.class));
                System.out.println(mh.invoke(1, 2)); // 相当于 C01Lambda1.lambda$main$2(1,2)

                b) 反射调用非静态方法
                MethodHandle mh2 = MethodHandles.lookup().findVirtual(MyLambda.class, "apply",
                        MethodType.methodType(Integer.class, Integer.class, Integer.class));
                System.out.println(mh2.invoke(new MyLambda(), 3, 4)); // 相当于通过 new MyLambda() 对象执行了 .apply(3,4)

                c) 反射调用构造
                MethodHandle mh3 = MethodHandles.lookup().findConstructor(MyLambda.class, MethodType.methodType(void.class));
                System.out.println(mh3.invoke()); // 相当于 new MyLambda()
         */

//        MethodHandles.Lookup lookup = MethodHandles.lookup();
//        MethodHandle impl = lookup.findStatic(C01Lambda1.class, "lambda$main$2",
//                MethodType.methodType(Integer.class, Integer.class, Integer.class));
//        // 内部：生成函数对象所需的类 ASM
//        CallSite cs = LambdaMetafactory.metafactory(
//                lookup,                                                             // 1. lookup
//                "apply",                                                            // 2. 接口方法名
//                MethodType.methodType(BinaryOperator.class),                        // 3. 创建函数对象工厂方法长相 BinaryOperator factory()
//                MethodType.methodType(Object.class, Object.class, Object.class),    // 4. 接口方法长相
//                impl,                                                               // 5. 实现方法 (本例就是下面的静态方法 lambda$main$2)
//                MethodType.methodType(Integer.class, Integer.class, Integer.class)  // 6. 函数对象实际长相
//        );
//
//        // BinaryOperator factory() {return new MyLambda()}
//        MethodHandle mh = cs.getTarget(); // 就是函数对象工厂方法
//        BinaryOperator<Integer> invoke = (BinaryOperator<Integer>) mh.invoke();
//        System.out.println(invoke.apply(5, 6));
    }

    static final class MyLambda implements BinaryOperator<Integer> {
        private MyLambda() {}
        @Override
        public Integer apply(Integer a, Integer b) {
            return lambda$main$2(a, b);
        }
    }

    private static Integer lambda$main$2(Integer a, Integer b) {
        return a + b;
    }

}

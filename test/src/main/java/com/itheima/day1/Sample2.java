package com.itheima.day1;

// 要想成为合格函数：引用的外部数据必须是不可变的（除参数外）
public class Sample2 {

    public static void main(String[] args) {
        System.out.println(pray("张三"));
        System.out.println(pray("张三"));
        System.out.println(pray("张三"));
//        buddha.name = "魔王";
        System.out.println(pray("张三"));
    }

//    static class Buddha {
//        final String name;
//
//        public Buddha(String name) {
//            this.name = name;
//        }
//    }

    record Buddha(String name) {}

    static Buddha buddha = new Buddha("如来");

    static String pray(String person) {
        return (person + "向[" + buddha.name + "]虔诚祈祷");
    }
}

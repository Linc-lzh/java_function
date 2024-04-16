package com.itheima.day5;

public class Test {
    public static void main(String[] args) {
        String a = "x";
        System.out.println(a + "y");
    }

    public class Vechicle {
        public void test(Car x, SportsCar y) {}
    }

    public class Car extends Vechicle {
    }

    public class SportsCar extends Car {
    }

    void  test() {
        Vechicle v = new Vechicle();
        Car c = new Car();
        SportsCar sporty = new SportsCar();

        c.test(sporty,sporty);
    }
}

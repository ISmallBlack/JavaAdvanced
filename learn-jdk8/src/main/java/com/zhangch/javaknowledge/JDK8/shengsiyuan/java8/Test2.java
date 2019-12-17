package com.zhangch.javaknowledge.JDK8.shengsiyuan.java8;


@FunctionalInterface
interface MyInterface {

    void test();

    String toString();

    //不符合函数式接口的定义
    // void tes1t();
    //符合函数式接口的定义  toString 是overriding自Object
    //String toString();
}

public class Test2 {

    public void myTest(MyInterface myInterface) {
        System.out.println(1);
        myInterface.test();
        System.out.println(2);
    }

    public static void main(String[] args) {
        Test2 test2 = new Test2();

        test2.myTest(() -> {
            System.out.println("mytest");
        });

        System.out.println("----------");

        MyInterface myInterface = () -> {
            System.out.println("hello");
        };
        /**
         * class com.zhangch.javaknowledge.JDK8.shengsiyuan.java8.Test2$$Lambda$2/1747585824
         * class java.lang.Object
         * interface com.zhangch.javaknowledge.JDK8.shengsiyuan.java8.MyInterface
         *
         * 说明函数式接口其实也就是 实现了对应的接口的 内部实现类
         */
        System.out.println(myInterface.getClass());
        System.out.println(myInterface.getClass().getSuperclass());
        System.out.println(myInterface.getClass().getInterfaces()[0]);
    }
}

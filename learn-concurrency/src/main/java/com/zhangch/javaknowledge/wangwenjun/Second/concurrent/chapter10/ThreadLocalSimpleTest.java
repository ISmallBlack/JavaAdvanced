package com.zhangch.javaknowledge.wangwenjun.Second.concurrent.chapter10;

/***************************************
 * @author:Alex Wang
 * @Date:2017/3/23 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class ThreadLocalSimpleTest {

    private static ThreadLocal<String> threadLocal = ThreadLocal.withInitial(() -> "Alex");

    //JVM start main thread
    public static void main(String[] args) throws InterruptedException {
//        threadLocal.set("Alex");
        Thread.sleep(1000);
        String value = threadLocal.get();
        System.out.println(value);
    }
}
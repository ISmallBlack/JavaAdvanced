package com.zhangch.javaknowledge.wangwenjun.First.chapter12;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class MyThread implements Runnable {

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " -> start");
            TimeUnit.SECONDS.sleep(10);
             //随机发生异常
            if (ThreadLocalRandom.current().nextInt(10) > 5) {
                throw new RuntimeException(Thread.currentThread().getName() + "发生异常");
            }
            System.out.println(Thread.currentThread().getName() + " -> end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
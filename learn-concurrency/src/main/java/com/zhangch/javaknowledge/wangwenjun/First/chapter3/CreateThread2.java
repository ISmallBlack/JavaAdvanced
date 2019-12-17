package com.zhangch.javaknowledge.wangwenjun.First.chapter3;

import java.util.Arrays;

/***************************************

 * 默认情况下Thread和Main同属一个ThreadGroup
 *
 ***************************************/
public class CreateThread2 {
    public static void main(String[] args) {
        Thread t = new Thread() {
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
        System.out.println(t.getThreadGroup());
        System.out.println(Thread.currentThread().getName());
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        System.out.println(threadGroup.getName());
        /**
         * 此时活跃线程数是 3
         */
        System.out.println(threadGroup.activeCount());

        Thread[] threads = new Thread[threadGroup.activeCount()];
        threadGroup.enumerate(threads);

        Arrays.asList(threads).forEach(System.out::println);
    }
}

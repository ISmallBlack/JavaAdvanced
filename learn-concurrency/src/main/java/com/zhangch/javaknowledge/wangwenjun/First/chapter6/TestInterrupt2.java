package com.zhangch.javaknowledge.wangwenjun.First.chapter6;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.MIN_PRIORITY;

/**
 * 如果interrupt调用是在可中断方法之前调用，可中断方法一定会处理中断，像上面的例子，interrupt方法极可能在run未进入sleep的 时候就调用了
 * ，但sleep检测到中断，就会处理该中断。如果在可中断方法正在执行中的时候调用interrupt，
 * 会怎么样呢？这就要看可中断方法处理 中断的时机了，只要可中断方法能检测到中断状态为true，就应该处理中断。让我们为开头的那段代码加上中断处理。
 */
public class TestInterrupt2 {
    public static void main(String[] args) throws Exception {
        Thread t = new MyThread();
        t.start();
        TimeUnit.SECONDS.sleep(1);//如果不能看到处理过程中被中断的情形，启用这句再看看
        t.interrupt();
        System.out.println("已调用线程的interrupt方法");
    }
    static class MyThread extends Thread {
        public void run() {
            int num;
            try {
                num = longTimeRunningNonInterruptMethod(1000000000, 0);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("长时间任务运行结束,num=" + num);
            System.out.println("线程的中断状态:" + Thread.interrupted());
        }
        private int longTimeRunningNonInterruptMethod(int count, int initNum) throws InterruptedException {
            System.out.println("正式处理前线程已经被请求中断:" + isInterrupted());
            if(interrupted()) {
                throw new InterruptedException("正式处理前线程已经被请求中断");
            }
            for(int i=0; i<count; i++) {
                for(int j=0; j<Integer.MAX_VALUE * 1000000000 * 100000000 * 1000000000; j++) {
                    initNum ++;
                }
                //假如这就是一个合适的地方
                //这段代码中检测中断用了Thread的静态方法interrupted，它将中断状态置为false，并将之前的状态返回，而isInterrupted只是检测中断，
                // 并不改变中断状态。一般来说，处理过了中断请求，应该将其状态置为false。但具体还要看实际情形。
                if(isInterrupted()){
                    System.out.println("线程正在处理过程中被中断:" + isInterrupted());
                }
                if(interrupted()) {
                    //回滚数据，清理操作等
                    throw new InterruptedException("线程正在处理过程中被中断");
                }
            }

            return initNum;
        }
    }
}
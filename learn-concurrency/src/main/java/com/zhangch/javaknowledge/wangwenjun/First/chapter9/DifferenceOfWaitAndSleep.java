
package com.zhangch.javaknowledge.wangwenjun.First.chapter9;

import java.util.stream.Stream;

/***************************************
 * @author:Alex Wang
 * @Date:2017/2/20 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class DifferenceOfWaitAndSleep {
    /**
     *  sleep 是属于Thread的方法  wait是属于Object的方法
     *  sleep不会释放锁  wait会释放锁
     *  wait 需要配合 synchronized 使用
     *  sleep不需要手动唤醒 wait需要
     */
    private final static Object LOCK = new Object();

    public static void main(String[] args) {
        Stream.of("T1", "T2").forEach(name ->
                new Thread(name) {
                    @Override
                    public void run() {
                        m2();
                    }
                }.start()
        );
    }

    public static void m1() {
        synchronized (LOCK) {
            try {
                System.out.println("The Thread " + Thread.currentThread().getName() + " enter.");
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void m2() {
        synchronized (LOCK) {
            try {
                System.out.println("The Thread " + Thread.currentThread().getName() + " enter.");
                LOCK.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
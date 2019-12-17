package com.zhangch.javaknowledge.wangwenjun.First.chapter8;

/***************************************
 * @author:Alex Wang
 * @Date:2017/2/19 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class DeadLockTest {
    public static void main(String[] args) {
        OtherService otherService = new OtherService();
        DeadLock deadLock = new DeadLock(otherService);
        otherService.setDeadLock(deadLock);
        new Thread(() -> {
            while (true) {
                deadLock.m1();
            }
        }).start();

        new Thread(() -> {
            while (true)
                otherService.s2();
        }).start();
    }
}
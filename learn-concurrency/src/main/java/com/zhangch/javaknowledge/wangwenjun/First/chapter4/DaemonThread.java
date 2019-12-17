package com.zhangch.javaknowledge.wangwenjun.First.chapter4;

/***************************************
 * @author:Alex Wang
 * @Date:2017/2/17 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class DaemonThread {

    public static void main(String[] args) throws InterruptedException {

        Thread t = new Thread() {

            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName() + " running");
                    Thread.sleep(100000);
                    System.out.println(Thread.currentThread().getName() + " done.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }; //new
        t.start();
        //守护线程 作用  连接线程做业务 需要开辟一个 心跳子线程进行health check 此时需要把 心跳子线程设置为守护线程
        //这样业务线程结束后 心跳线程随之结束 防止线程泄露
        t.setDaemon(true);
        //runnable  ->running| ->dead| ->blocked


        Thread.sleep(5_000);   //JDK1.7
        System.out.println(Thread.currentThread().getName());
    }
}

/**
 * A<---------------------------------->B
 *  ->daemonThread(health check)
 * */
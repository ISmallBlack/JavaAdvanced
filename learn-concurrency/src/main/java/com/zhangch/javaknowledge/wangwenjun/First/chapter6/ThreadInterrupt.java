package com.zhangch.javaknowledge.wangwenjun.First.chapter6;

/***************************************
 * @author:Alex Wang
 * @Date:2017/2/19 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class ThreadInterrupt {

    private static final Object MONITOR = new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread t = new Thread() {
            @Override
            public void run() {
                while (true) {
                    synchronized (MONITOR) {
                        try {
                            MONITOR.wait(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            //此处虽然收到了打断信号 但是打断状态还是 false
                            System.out.println("bbb"+isInterrupted());
                        }
                    }
                }
            }
        };

        t.start();
       // Thread.sleep(100);
        System.out.println("ss"+t.isInterrupted());
        t.interrupt();
        System.out.println("aaa"+t.isInterrupted());
        System.out.println("ddd"+t.isInterrupted());

       // t.stop();

      /*  Thread t = new Thread(() -> {
            while (true) {
                synchronized (MONITOR) {
                    try {
                        MONITOR.wait(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        System.out.println(Thread.interrupted());
                    }
                }
            }
        });
        interrupted 有实例方法和类方法其实一样的没有区别
        */

   /*     Thread t = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    System.out.println("print Something");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
     //   t.setDaemon(true);
        t.start();
        Thread main = Thread.currentThread();
        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            main.interrupt();
            System.out.println("interrupt");
        });

        t2.start();
        try {
            // join的是main线程 就是插队到main线程之前 其实是main线程执行wait
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //---------------------------

        System.out.println("mainAlready");*/

    }
}

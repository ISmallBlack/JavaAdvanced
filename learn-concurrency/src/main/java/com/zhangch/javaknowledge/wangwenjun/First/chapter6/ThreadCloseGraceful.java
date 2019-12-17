package com.zhangch.javaknowledge.wangwenjun.First.chapter6;

/***************************************
 * @author:Alex Wang
 * @Date:2017/2/19 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class ThreadCloseGraceful {

    private static class Worker extends Thread {
        private volatile boolean start = true;

        @Override
        public void run() {
            while (start) {
                //
                int i = 0;
                while (i<15){
                    try {
                        Thread.sleep(1000);
                        System.out.println("print Something"+i);
                        i++;
                    } catch (InterruptedException e) {
                        System.out.println("发出中断继续执行.....");
                        e.printStackTrace();
                    }
                }
            }
        }

        public void shutdown() {
            this.start = false;
        }
    }

    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        worker.shutdown();
        //线程中断 但是线程体的内容还是会继续执行的
        worker.interrupt();
    }
}

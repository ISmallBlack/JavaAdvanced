package com.zhangch.javaknowledge.wangwenjun.First.chapter9;

import java.util.stream.Stream;

/***************************************
 * @author:Alex Wang
 * @Date:2017/2/19 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class ProduceConsumerVersion2 {

    private int i = 0;

    final private Object LOCK = new Object();

    private volatile boolean isProduced = false;

    public void produce() {
        synchronized (LOCK) {
            if (isProduced) {
                try {
                    System.out.println(Thread.currentThread().getName()+"P->"+"wait");
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                i++;
                System.out.println(Thread.currentThread().getName()+"P->" + i);
                LOCK.notify();
                isProduced = true;
            }
        }
    }

    public void consume() {
        synchronized (LOCK) {
            if (isProduced) {
                System.out.println(Thread.currentThread().getName()+"C->" + i);
                LOCK.notify();
                isProduced = false;
            } else {
                try {
                    System.out.println(Thread.currentThread().getName()+"C->"+"wait");
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 当前版本是单生产者 单消费者
     * 如果是多生产者多消费者下 会假死   原因：随机唤醒线程
     *
     * P1P->1
     * P1P->wait
     * P2P->wait
     * C1C->1
     * C1C->wait
     * P1P->2
     * P1P->wait
     * P2P->wait
     * C2C->2
     * C2C->wait
     * C1C->wait
     *
     *
     * p1 生产 无唤醒
     * p1  wait
     * p2  wait
     *
     * c1 消费 唤醒  p1
     * c1 wait
     * p1 生产  唤醒 p2
     * p1 wait
     * p2 wait
     *
     * c2 消费 唤醒 c1
     * c1 wait
     * c2 wait
     *
     * 至此所有线程 wait
     * @param args
     */
    public static void main(String[] args) {
        ProduceConsumerVersion2 pc = new ProduceConsumerVersion2();
        Stream.of("P1", "P2").forEach(n ->
                new Thread(n) {
                    @Override
                    public void run() {
                        while (true)
                            pc.produce();
                    }
                }.start()
        );
        Stream.of("C1", "C2").forEach(n ->
                new Thread(n) {
                    @Override
                    public void run() {
                        while (true)
                            pc.consume();
                    }
                }.start()
        );
    }
}
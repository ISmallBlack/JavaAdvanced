package com.zhangch.javaknowledge.wangwenjun.Second.concurrent.chapter13;

import java.util.LinkedList;

/***************************************
 * @author:Alex Wang
 * @Date:2017/3/24 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class MessageQueue {

    private final LinkedList<Message> queue;

    private final static int DEFAULT_MAX_LIMIT = 100;

    private final int limit;

    public MessageQueue() {
        this(DEFAULT_MAX_LIMIT);
    }

    public MessageQueue(final int limit) {
        this.limit = limit;
        this.queue = new LinkedList<>();
    }

    /**
     * 生产者
     * @param message
     * @throws InterruptedException
     */
    public void put(final Message message) throws InterruptedException {
        synchronized (queue) {
            while (queue.size() > limit) {
                queue.wait();
            }

            queue.addLast(message);
            queue.notifyAll();
        }
    }

    /**
     * 消费者
     * @return
     * @throws InterruptedException
     */
    public Message take() throws InterruptedException {
        synchronized (queue) {
            while (queue.isEmpty()) {
                queue.wait();
            }

            Message message = queue.removeFirst();
            queue.notifyAll();
            return message;
        }
    }

    public int getMaxLimit() {
        return this.limit;
    }

    public int getMessageSize() {
        synchronized (queue) {
            return queue.size();
        }
    }
}
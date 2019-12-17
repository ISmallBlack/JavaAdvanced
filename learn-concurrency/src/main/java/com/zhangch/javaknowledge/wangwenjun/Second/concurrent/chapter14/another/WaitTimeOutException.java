package com.zhangch.javaknowledge.wangwenjun.Second.concurrent.chapter14.another;

/**
 * 当子任务线程执行超时的时候会抛出该异常
 */
public class WaitTimeOutException extends Exception {

    public WaitTimeOutException(String message) {
        super(message);
    }
}

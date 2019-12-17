package com.zhangch.javaknowledge.designpatterns.singleton;

/**
 * Created by geely
 */
public class ThreadLocalInstance {
    private static final ThreadLocal<ThreadLocalInstance> threadLocalInstanceThreadLocal
             = ThreadLocal.withInitial(() -> new ThreadLocalInstance());
    private ThreadLocalInstance(){

    }

    public static ThreadLocalInstance getInstance(){
        return threadLocalInstanceThreadLocal.get();
    }

}

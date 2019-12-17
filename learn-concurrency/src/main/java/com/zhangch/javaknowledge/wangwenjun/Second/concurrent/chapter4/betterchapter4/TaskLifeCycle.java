package com.zhangch.javaknowledge.wangwenjun.Second.concurrent.chapter4.betterchapter4;

public interface TaskLifeCycle<T> {

    void onStart(Thread thread);

    void onRunning(Thread thread);

    void onFinish(Thread thread, T result);

    void onError(Thread thread, Exception e);
    //生命周期接口的空实现
     class EmptyLifeCycle<T> implements TaskLifeCycle<T>{

        @Override
        public void onStart(Thread thread) {

        }

        @Override
        public void onRunning(Thread thread) {

        }

        @Override
        public void onFinish(Thread thread, T result) {

        }

        @Override
        public void onError(Thread thread, Exception e) {

        }
    }
}

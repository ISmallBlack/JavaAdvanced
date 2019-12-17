package com.zhangch.javaknowledge.wangwenjun.Second.concurrent.chapter4.betterchapter4;

public interface Observable {
    public enum Cycle {
        STARTED,RUNNING, ERROR, DONE
    }

    Cycle getCycle();

    void start();

    void interrupt();
}

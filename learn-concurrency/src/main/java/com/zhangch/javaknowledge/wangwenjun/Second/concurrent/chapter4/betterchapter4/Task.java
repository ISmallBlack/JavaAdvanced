package com.zhangch.javaknowledge.wangwenjun.Second.concurrent.chapter4.betterchapter4;

/**
 * Task接口
 * @param <T>
 */
@FunctionalInterface
public interface Task<T> {
    T call();
}

package com.zhangch.javaknowledge.wangwenjun.Second.concurrent.chapter8.anotherbetter;

/**
 * 回调接口，任务结束后回调通知任务发布者
 * 可以丰富 成功 失败之类的
 * @param <T>
 */
@FunctionalInterface
public interface CallBack<T> {

    //任务完成后会调用此接口，其中T为任务执行的结果
    void call(T t);

}

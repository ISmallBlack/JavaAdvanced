package com.zhangch.javaknowledge.wangwenjun.Second.concurrent.chapter4.betterchapter4;

import java.util.concurrent.TimeUnit;

/**
 * 功能说明: <br>
 * 系统版本: 1.0 <br>
 * 开发人员: zhanch
 * 开发时间: 2019/12/8<br>
 * <br>
 */
public class Test {

    public static void main(String[] args) {
        Observable observable = new ObservableThread<>(new TestLifeCycle<>(),() ->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Finsh done.");
            return null;
        });
        observable.start();
    }

    //生命周期接口的空实现
    static  class TestLifeCycle<T> implements TaskLifeCycle<T>{

        @Override
        public void onStart(Thread thread) {
            System.out.println(thread.getName()+"onStart.");
        }

        @Override
        public void onRunning(Thread thread) {
            System.out.println(thread.getName()+"onRunning.");
        }

        @Override
        public void onFinish(Thread thread, T result) {
            System.out.println(thread.getName()+"onFinish.");
        }

        @Override
        public void onError(Thread thread, Exception e) {
            System.out.println(thread.getName()+"onError.");
        }
    }
}

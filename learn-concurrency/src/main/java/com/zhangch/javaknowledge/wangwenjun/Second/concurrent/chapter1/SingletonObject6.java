package com.zhangch.javaknowledge.wangwenjun.Second.concurrent.chapter1;

/***************************************
 * @author:Alex Wang
 * @Date:2017/3/12 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class SingletonObject6 {

    private SingletonObject6() {

    }

    /**
     * 依赖类本身的初始化 单例
     */
    private static class InstanceHolder {
        private final static SingletonObject6 instance = new SingletonObject6();
    }

    public static SingletonObject6 getInstance() {
        return InstanceHolder.instance;
    }
}
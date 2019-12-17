package com.zhangch.javaknowledge.wangwenjun.Second.concurrent.chapter4;

import java.util.ArrayList;
import java.util.List;

/***************************************
  *
 * 主题类 包含多个观察者对象
 ***************************************/
public class Subject {

    private List<Observer> observers = new ArrayList<>();

    private int state;

    public int getState() {
        return this.state;
    }

    /**
     * 监听状态变化
     * @param state
     */
    public void setState(int state) {
        if (state == this.state) {
            return;
        }
        this.state = state;
        notifyAllObserver();
    }

    /**
     * 注册观察者
     * @param observer
     */
    public void attach(Observer observer) {
        observers.add(observer);
    }

    /**
     * 广播观察者
     */
    private void notifyAllObserver() {
        observers.stream().forEach(Observer::update);
    }
}
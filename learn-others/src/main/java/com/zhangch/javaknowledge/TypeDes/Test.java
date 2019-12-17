package com.zhangch.javaknowledge.TypeDes;

import cn.hutool.core.date.TimeInterval;

import java.util.concurrent.atomic.LongAdder;

/**
 * 功能说明: <br>
 * 系统版本: 1.0 <br>
 * 开发人员: zhanch
 * 开发时间: 2019/12/16<br>
 * <br>
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        TimeInterval timeInterval = new TimeInterval();
        LongAdder parseXmllongAdder = new LongAdder();
        timeInterval.start();
        Thread.sleep(1000);
        parseXmllongAdder.add(timeInterval.interval());
        System.out.println(parseXmllongAdder.longValue());
        LongAdder parseXmllongAdder1 = new LongAdder();
        timeInterval.start();
        Thread.sleep(1000);
        parseXmllongAdder1.add(timeInterval.interval());
        System.out.println(parseXmllongAdder1.longValue());
    }
}


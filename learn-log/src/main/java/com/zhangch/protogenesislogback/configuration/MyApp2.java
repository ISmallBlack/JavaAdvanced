package com.zhangch.protogenesislogback.configuration;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  配置信息 增加
 *  <configuration debug="true">
 *      也会打印内部信息
 *
 *    //当配置文件更改时，自动加载
 *   <configuration scan="true" scanPeriod="2 seconds"
 */
public class MyApp2 {
    public static final Logger LOGGER = LoggerFactory.getLogger(MyApp2.class);

    public static void main(String[] args) throws InterruptedException {
        while (true){
            LOGGER.info("Entering application.");
            Foo foo = new Foo();
            foo.doIt();
            LOGGER.info("Exiting application.");
            Thread.sleep(5000);
        }
    }   
}
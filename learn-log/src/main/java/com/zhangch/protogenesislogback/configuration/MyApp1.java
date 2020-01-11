package com.zhangch.protogenesislogback.configuration;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 默认配置 打印内部状态信息
 */
public class MyApp1 {
    public static final Logger LOGGER = LoggerFactory.getLogger(MyApp1.class);

    public static void main(String[] args) {
        LoggerContext lc = (LoggerContext)LoggerFactory.getILoggerFactory();
        StatusPrinter.print(lc);
        LOGGER.info("Entering application.");

        Foo foo = new Foo();
        foo.doIt();
        LOGGER.info("Exiting application.");
    }   
}
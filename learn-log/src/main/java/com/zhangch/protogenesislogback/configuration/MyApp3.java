package com.zhangch.protogenesislogback.configuration;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class MyApp3 {
    public static final Logger LOGGER = LoggerFactory.getLogger(MyApp3.class);

    public static void main(String[] args) throws InterruptedException {
        LOGGER.info("Entering application.");
        Foo foo = new Foo();
        foo.doIt();
        LOGGER.info("Exiting application.");
    }   
}
package com.zhangch.protogenesislogback.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Foo {

    public static final Logger LOGGER = LoggerFactory.getLogger(Foo.class);

    public void doIt() {
        LOGGER.debug("Did it again!");
    }
}
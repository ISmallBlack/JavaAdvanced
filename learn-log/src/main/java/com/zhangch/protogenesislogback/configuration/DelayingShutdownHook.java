package com.zhangch.protogenesislogback.configuration;

import ch.qos.logback.core.hook.ShutdownHookBase;

import ch.qos.logback.core.util.Duration;

public class DelayingShutdownHook extends ShutdownHookBase {
    /**
     * The default is no delay before shutdown.
     */
    public static final Duration DEFAULT_DELAY = Duration.buildByMilliseconds(0);

    /**
     * The delay in milliseconds before the ShutdownHook stops the logback context
     */
    private Duration delay = DEFAULT_DELAY;

    public DelayingShutdownHook() {
    }

    public Duration getDelay() {
        return delay;
    }

    /**
     * The duration to wait before shutting down the current logback context.
     *
     * @param delay
     */
    public void setDelay(Duration delay) {
        this.delay = delay;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(30000);
            System.out.println("ShutdownHook......");
        } catch (InterruptedException e) {
        }
        super.stop();
    }
}
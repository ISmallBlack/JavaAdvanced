package com.zhangch.javaknowledge.JDK8.anotherjava8.examples.chapter4;

// BEGIN body
public interface Child extends Parent {

    @Override
    public default void welcome() {
        message("Child: Hi!");
    }

}
// END body

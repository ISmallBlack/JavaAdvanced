package com.zhangch.javaknowledge.JDK8.anotherjava8.examples.chapter4;

public class ParentImpl implements Parent {

    private String body;

    @Override
    public void message(String body) {
        this.body = body;
    }

    @Override
    public void welcome() {
        message("Classssss Parent: Hi!");
    }

    @Override
    public String getLastMessage() {
        return body;
    }

}

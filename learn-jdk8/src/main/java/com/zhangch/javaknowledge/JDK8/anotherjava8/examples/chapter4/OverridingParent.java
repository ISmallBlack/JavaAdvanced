package com.zhangch.javaknowledge.JDK8.anotherjava8.examples.chapter4;

// BEGIN body
public class OverridingParent extends ParentImpl {

    @Override
    public void welcome() {
        message("Class Parent: Hi!");
    }

}
// END body

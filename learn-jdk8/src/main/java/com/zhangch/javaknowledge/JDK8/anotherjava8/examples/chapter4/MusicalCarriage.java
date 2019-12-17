package com.zhangch.javaknowledge.JDK8.anotherjava8.examples.chapter4;

// BEGIN body
public class MusicalCarriage
        implements Carriage, Jukebox {

    @Override
    public String rock() {
        return Carriage.super.rock();
    }

}
// END body

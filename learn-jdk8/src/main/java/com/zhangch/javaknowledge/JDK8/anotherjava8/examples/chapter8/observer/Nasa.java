package com.zhangch.javaknowledge.JDK8.anotherjava8.examples.chapter8.observer;

// BEGIN Nasa
public class Nasa implements LandingObserver {
    @Override
    public void observeLanding(String name) {
        if (name.contains("Apollo")) {
            System.out.println("We made it!");
        }
    }
}
// END Nasa

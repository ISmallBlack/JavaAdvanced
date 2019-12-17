package com.zhangch.javaknowledge.wangwenjun.First.chapter12;

public class ThreadGroupDestroy {
                                                                              
    public static void main(String[] args) {                                  
        ThreadGroup group = new ThreadGroup("TestGroup");                     
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();      
        //before destroy                                                      
        System.out.println("group.isDestroyed=" + group.isDestroyed());       
        mainGroup.list();                                                     
                                                                              
        group.destroy();                                                      
        //after destroy                                                       
        System.out.println("group.isDestroyed=" + group.isDestroyed());       
        mainGroup.list();                                                     
    }                                                                         
                                                                              
}
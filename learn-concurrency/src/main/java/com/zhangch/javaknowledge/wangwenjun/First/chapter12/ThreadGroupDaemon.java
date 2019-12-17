package com.zhangch.javaknowledge.wangwenjun.First.chapter12;

import java.util.concurrent.TimeUnit;

/**
 * 线程组设置为守护线程组，并不会影响其线程是否为守护线程，仅仅表示当它内部没有active的线程的时候，会自动destroy
 */
public class ThreadGroupDaemon {
                                                                               
    public static void main(String[] args) throws InterruptedException {       
        ThreadGroup group1 = new ThreadGroup("group1");                        
        new Thread(group1, () -> {                                             
            try {                                                              
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {                                 
                e.printStackTrace();                                           
            }                                                                  
        }, "group1-thread1").start();                                          
        ThreadGroup group2 = new ThreadGroup("group2");                        
        new Thread(group2, () -> {                                             
            try {                                                              
                TimeUnit.SECONDS.sleep(1);                                     
            } catch (InterruptedException e) {                                 
                e.printStackTrace();                                           
            }                                                                  
        }, "group1-thread2").start();                                          
        group2.setDaemon(true);                                                
                                                                               
        TimeUnit.SECONDS.sleep(3);                                             
        System.out.println(group1.isDestroyed());                              
        System.out.println(group2.isDestroyed());                              
    }                                                                          
}
package com.zhangch.javaknowledge.wangwenjun.Second.concurrent.chapter12.anotherdemo;

/**
 * Balking 设计模式测试
 */
public class BalkingTest {

    public static void main(String[] args){
        new DocumentEditThread("D:\\project_java\\ideaworkspace_learn\\CodeDemo\\java\\JavaKnowledge\\designpattern\\src\\main\\java\\com\\zhangch\\javaknowledge\\concurrency\\wangwenjun\\Second\\concurrent\\chapter12\\anotherdemo","document.txt").start();
    }
}

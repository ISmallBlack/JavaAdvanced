package com.zhangch.javaknowledge.wangwenjun.First.chapter3;

/***************************************
 * 指定线程栈帧深度
 * stackSize 每个栈的大小
 ***************************************/
public class CreateThread4 {

    private static int counter = 1;

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(null, new Runnable() {
            @Override
            public void run() {
                try {
                    add(1);
                } catch (Error e) {
                    System.out.println(counter);
                }
            }

            private void add(int i) {
                counter++;
                add(i + 1);
            }
        }, "Test111", 1 << 24);
        t1.start();
        Thread.sleep(100000);
    }
    //-Xss 可以指定stackSize
}

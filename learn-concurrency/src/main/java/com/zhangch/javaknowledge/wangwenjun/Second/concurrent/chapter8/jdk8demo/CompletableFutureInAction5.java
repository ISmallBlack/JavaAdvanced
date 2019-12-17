package com.zhangch.javaknowledge.wangwenjun.Second.concurrent.chapter8.jdk8demo;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static java.util.stream.Collectors.toList;

/***************************************
 * @author:Alex Wang
 * @Date:2016/11/13 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class CompletableFutureInAction5 {
    public static void main(String[] args) throws InterruptedException {
        //多个前置任务完成之后 调用Runnable  Runnable  也会按照顺序 执行
        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " is running.");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " 111 is running.");

            return 1;
        }).runAfterBoth(CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " is running.");
            System.out.println(Thread.currentThread().getName() + " 222 is running.");
            return 2;
        }), () -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("1done");
        }).runAfterBoth(CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " is running.");
            System.out.println(Thread.currentThread().getName() + " 333 is running.");
            return 2;
        }), () -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("2done");
        });
        System.out.println(Thread.currentThread().getName() + " 4444 is running.");


  /*      //两个任意完成一个就可以 后续 后续是Function
        CompletableFuture.supplyAsync(() -> {
            System.out.println("I am future 1");
            return CompletableFutureInAction1.get();
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            System.out.println("I am future 2");
            return CompletableFutureInAction1.get();
        }), v -> v * 10)
                .thenAccept(System.out::println);

        //两个任意完成一个就可以 后续 后续是Consumer
        CompletableFuture.supplyAsync(() -> {
            System.out.println("I am future 1");
            return CompletableFutureInAction1.get();
        }).acceptEither(CompletableFuture.supplyAsync(() -> {
            System.out.println("I am future 2");
            return CompletableFutureInAction1.get();
        }), System.out::println);*/
/*
        //两个任意完成一个就可以 后续 后续是Runnable 异步解耦
        CompletableFuture.supplyAsync(() -> {
            System.out.println("I am future 1");
            return CompletableFutureInAction1.get();
        }).runAfterEither(CompletableFuture.supplyAsync(() -> {
            System.out.println("I am future 2");
            return CompletableFutureInAction1.get();
        }), () -> System.out.println("done."));
*/
     //判断执行结果
     /*   List<CompletableFuture<Double>> collect = Arrays.asList(1, 2, 3, 4)
                .stream()
                .map(i -> CompletableFuture.supplyAsync(CompletableFutureInAction1::get))
                .collect(toList());

        CompletableFuture.anyOf(collect.toArray(new CompletableFuture[collect.size()]))
                .thenRun(() -> System.out.println("done"));*/


        Thread.currentThread().join();
    }
}

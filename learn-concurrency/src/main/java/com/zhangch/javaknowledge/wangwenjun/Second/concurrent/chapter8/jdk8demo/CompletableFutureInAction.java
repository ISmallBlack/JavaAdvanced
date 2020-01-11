package com.zhangch.javaknowledge.wangwenjun.Second.concurrent.chapter8.jdk8demo;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.function.BiConsumer;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;


/***************************************
 * @author:Alex Wang
 * @Date:2016/11/8 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class CompletableFutureInAction {

    private static final Random random = new Random(System.currentTimeMillis());

    public static void demo1(){
        CompletableFuture<Double> future = new CompletableFuture<>();
        new Thread(() -> {
            try {
                Thread.sleep(1000L);
                future.complete(1000d);
            } catch (InterruptedException e) {
                future.completeExceptionally(e);
            }

        }).start();
        System.out.println("..............");
        future.whenComplete((v, t) -> {
            System.out.println(v);
            t.printStackTrace();
        });
    }
    private static double get() {
        try {
            Thread.sleep(1000L);
            double v = 12d;
            System.out.println(v);
            return v;
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }
    public static void demo2() throws InterruptedException {
        //单纯打印会失败的 因为内部是 守护线程 必须要将 主线程join
        CompletableFuture<Double> future = CompletableFuture.supplyAsync(CompletableFutureInAction::get);
        future.whenComplete((v, t) -> {
            System.out.println(v);
            t.printStackTrace();
        });
        Thread.currentThread().join();
    }

    public static void whenComplete() throws Exception {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            if(new Random().nextInt()%2>=0) {
                int i = 12/0;
            }
            System.out.println("run end ...");
        });

        future.whenComplete((t, action) -> {
            if(action == null){
                System.out.println("执行完成！");
            }else{
                System.out.println("执行完成22！"+action.getMessage());
            }
        });
        future.exceptionally(t -> {
            System.out.println("执行失败！"+t.getMessage());
            return null;
        });

        TimeUnit.SECONDS.sleep(2);
    }

    public static void demo3() throws InterruptedException {
        long start = System.currentTimeMillis();
        List<Double> doubles = Arrays.asList(random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble());
        System.out.println(doubles);
        List<CompletableFuture<Double>> futures = doubles
                .stream()
                .map(d -> CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(10000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return d * 10d;
                }))
                .collect(toList());

        List<Double> collect = futures.stream().parallel().map(f -> f.join()).collect(toList());
        System.out.println(collect);
        System.out.println(start-System.currentTimeMillis());
    }

    public static void demo4() throws InterruptedException, ExecutionException {
        Executor executor = Executors.newFixedThreadPool(2, r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });
        executor.execute(() -> System.out.println("sfsdfsfs"));

        Double value = CompletableFuture.supplyAsync(CompletableFutureInAction::get)
                .whenComplete((v, t) -> System.out.println(">>>>" + v))
                .thenCompose(i -> CompletableFuture.supplyAsync(() -> i + 10))
                .get();
        System.out.println(value);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
      //  demo1();
        demo2();

    }


}

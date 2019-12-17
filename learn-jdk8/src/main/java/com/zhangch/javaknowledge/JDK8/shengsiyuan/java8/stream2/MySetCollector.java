package com.zhangch.javaknowledge.JDK8.shengsiyuan.java8.stream2;

/**
 *
 * CONCURRENT：表示此收集器支持并发。是值多个线程操作一个可变容器 收集器的合并操作是不需要的  否则是多个线程操作多个容器 然后进行容器的合并
 * UNORDERED：表示该收集操作不会保留流中元素原有的顺序。
 * IDENTITY_FINISH：表示finisher参数只是标识而已，可忽略。
 *
 *
 */

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;
import static java.util.stream.Collector.Characteristics.UNORDERED;

public class MySetCollector<T> implements Collector<T, Set<T>, Set<T>>{

    @Override
    public Supplier<Set<T>> supplier() {
        System.out.println("supplier invoked!");
        return HashSet<T>::new;
    }

    @Override
    public BiConsumer<Set<T>, T> accumulator() {
        System.out.println("accumulator invoked!");
        return Set<T>::add;
    }

    @Override
    public BinaryOperator<Set<T>> combiner() {
        System.out.println("combiner invoked!");
        return (set1, set2) -> {
            set1.addAll(set2);
            return set1;
        };
    }

    @Override
    public Function<Set<T>, Set<T>> finisher() {
//        System.out.println("finisher invoked!");
//        return Function.identity();
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<Characteristics> characteristics() {
        System.out.println("characteristics invoked!");
        return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH, UNORDERED));
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "world", "welcome", "hello");
        Set<String> set = list.stream().collect(new MySetCollector<>());

        System.out.println(set);
    }
}

























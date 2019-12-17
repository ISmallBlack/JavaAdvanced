package com.zhangch.javaknowledge.JDK8.anotherjava8.answers.chapter3;

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


public class MySetCollector3<Character> implements Collector<Character, StringBuilder, String>{
    private final Supplier<StringBuilder> supplier;
    private final BiConsumer<StringBuilder, Character> accumulator;
    private final BinaryOperator<StringBuilder> combiner;
    private final Function<StringBuilder, String> finisher;
    private final Set<Characteristics> characteristics;
    MySetCollector3(Supplier<StringBuilder> supplier,
                  BiConsumer<StringBuilder, Character> accumulator,
                  BinaryOperator<StringBuilder> combiner,
                  Function<StringBuilder,String> finisher,
                  Set<Characteristics> characteristics) {
        this.supplier = supplier;
        this.accumulator = accumulator;
        this.combiner = combiner;
        this.finisher = finisher;
        this.characteristics = characteristics;
    }
    @Override
    public Supplier<StringBuilder> supplier() {
        return supplier;
    }

    @Override
    public BiConsumer<StringBuilder, Character> accumulator() {
        return accumulator;
    }

    @Override
    public BinaryOperator<StringBuilder> combiner() {
        return combiner;
    }

    @Override
    public Function<StringBuilder, String> finisher() {
      return finisher;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return characteristics;
    }
}

























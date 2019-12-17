package com.zhangch.javaknowledge.JDK8.shengsiyuan.java8.stream;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest5 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "world", "helloworld", "test");
        list.stream().map(String::toUpperCase).collect(Collectors.toList()).forEach(System.out::println);

        System.out.println("----------");

        List<Integer> list2 = Arrays.asList(1, 2, 3, 4, 5);
        list2.stream().map(item -> item * item).collect(Collectors.toList()).forEach(System.out::println);

        System.out.println("----------");

        Stream<List<Integer>> stream = Stream.of(Arrays.asList(1),
                Arrays.asList(2, 3), Arrays.asList(4, 5, 6));

        stream.flatMap(theList -> theList.stream()).map(item -> item * item).forEach(System.out::println);

        List<List<String>> listList = Arrays.asList(Arrays.asList("ssss"),Arrays.asList("ssss1","ssss2"));

        listList.stream().flatMap(list11 ->list11.stream()).map(String::toUpperCase).forEach(System.out::println);

    }
}

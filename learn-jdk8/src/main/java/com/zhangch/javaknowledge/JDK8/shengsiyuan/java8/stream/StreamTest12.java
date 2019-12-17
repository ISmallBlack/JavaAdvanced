package com.zhangch.javaknowledge.JDK8.shengsiyuan.java8.stream;


import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class StreamTest12 {

    public static void main(String[] args) {
        List<String> list1 = Arrays.asList("Hi", "Hello", "你好");
        List<String> list2 = Arrays.asList("zhangsan", "lisi", "wangwu", "zhaoliu");

        List<String> result = list1.stream()
                .flatMap(item -> list2.stream().map(item2 -> item + " " + item2))
                .collect(toList());


     //   result.forEach(System.out::println);




        List<String> resul2 = list1.stream()
                .map(item -> list2.stream().map(item2 -> item + " " + item2)).collect(toList()).stream().flatMap(stringStream -> stringStream)
         .collect(toList());
        resul2.forEach(System.out::println);
    }

}

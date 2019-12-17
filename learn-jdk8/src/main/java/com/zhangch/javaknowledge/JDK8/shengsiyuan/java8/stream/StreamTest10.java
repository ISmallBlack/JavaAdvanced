package com.zhangch.javaknowledge.JDK8.shengsiyuan.java8.stream;


import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class StreamTest10 {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "world", "hello world");

//        list.stream().mapToInt(item -> item.length()).filter(length -> length == 5).
//                findFirst().ifPresent(System.out::println);
      //流的本质是只有在最好终止操作的时候才会对源数据进行一次循环操作，操作的内容就是我们之前发布的所有指令
        list.stream().map(item -> {
            int length = item.length();
            System.out.println(item);
            return length;
        }).filter(length -> length == 5).collect(toList()).forEach(System.out::println);

    }
}

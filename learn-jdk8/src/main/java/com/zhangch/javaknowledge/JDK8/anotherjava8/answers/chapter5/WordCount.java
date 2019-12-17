package com.zhangch.javaknowledge.JDK8.anotherjava8.answers.chapter5;

import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class WordCount {

    public static Map<String, Long> countWords(Stream<String> names) {
        return names.collect(groupingBy(name -> name, counting()));
    }

}

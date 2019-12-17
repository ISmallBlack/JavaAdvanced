package com.zhangch.javaknowledge.JDK8.anotherjava8.exercises.chapter6;

import java.util.List;

public class BuggyReduce {

    public static int multiplyThrough(List<Integer> linkedListOfNumbers) {
        return linkedListOfNumbers.stream()
                                  .reduce(5, (acc, x) -> x * acc);
    }

}

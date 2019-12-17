package com.zhangch.javaknowledge.JDK8.anotherjava8.answers.chapter6;

import java.util.stream.IntStream;

public class SerialToParallel {

    public static int sumOfSquares(IntStream range) {
        return range.parallel()
                    .map(x -> x * x)
                    .sum();
    }

    public static int sequentialSumOfSquares(IntStream range) {
        return range.map(x -> x * x)
                    .sum();
    }

}

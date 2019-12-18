package com.zhangch.javaknowledge.demo;

/**
 * 功能说明: <br>
 * 系统版本: 1.0 <br>
 * 开发人员: zhanch
 * 开发时间: 2019/12/18<br>
 * <br>
 */
public class Test {
    public static void main(String[] args) {
        final int numberOfBuckets = (int) Math.sqrt(16);
        System.out.println(hash(30,100,8));
    }

    private static int hash(int i, int max, int numberOfBuckets) {
        return (int) ((double) i / max * (numberOfBuckets - 1));
    }
}

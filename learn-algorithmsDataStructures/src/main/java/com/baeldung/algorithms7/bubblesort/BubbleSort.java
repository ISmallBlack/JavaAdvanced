package com.baeldung.algorithms7.bubblesort;

import java.util.stream.IntStream;

/**
 * 冒泡排序
 */
public class BubbleSort {
    /**
     * 标准冒泡排序  IntStream.range(0, n - 1) 表名我们需要进行 n-1 次迭代
     * flatMap(i -> IntStream.range(1, n - i)) 表名我们进行 第 i 次排序时 我们只是处理 第一个元素到第n - i元素
     * @param arr
     */
    void bubbleSort(Integer[] arr) {
        int n = arr.length;
        IntStream.range(0, n - 1)
          .flatMap(i -> IntStream.range(1, n - i))
          .forEach(j -> {
              if (arr[j - 1] > arr[j]) {
                  int temp = arr[j];
                  arr[j] = arr[j - 1];
                  arr[j - 1] = temp;
              }
          });
    }

    /**
     * 冒泡最佳实践 对于近乎有序的数据 冒泡排序
     * 可以以很小的代价修复,使其成为完全有序序列
     * 冒泡排序是时间复杂度O(n^2)  空间复杂度 O(1)
     * @param arr
     */
    void optimizedBubbleSort(Integer[] arr) {
        int i = 0, n = arr.length;
        boolean swapNeeded = true;
        while (i < n - 1 && swapNeeded) {
            swapNeeded = false;
            for (int j = 1; j < n - i; j++) {
                if (arr[j - 1] > arr[j]) {
                    int temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                    swapNeeded = true;
                }
            }
            if (!swapNeeded)
                break;
            i++;
        }
    }
}

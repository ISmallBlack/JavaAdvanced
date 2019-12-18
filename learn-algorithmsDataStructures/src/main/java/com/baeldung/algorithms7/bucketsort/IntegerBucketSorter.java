package com.baeldung.algorithms7.bucketsort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * 桶排序 最坏的情况是 O(n^2)  即所有的数据唯一同一个桶且全是倒序
 *  最好的是均匀分布在桶中 O(n)
 */
public class IntegerBucketSorter implements Sorter<Integer> {

    private final Comparator<Integer> comparator;

    public IntegerBucketSorter(Comparator<Integer> comparator) {
        this.comparator = comparator;
    }

    public IntegerBucketSorter() {
        comparator = Comparator.naturalOrder();
    }

    public List<Integer> sort(List<Integer> arrayToSort) {

        List<List<Integer>> buckets = splitIntoUnsortedBuckets(arrayToSort);

        for(List<Integer> bucket  : buckets){
            bucket.sort(comparator);
        }

        return concatenateSortedBuckets(buckets);
    }

    public List<Integer> anotherAort(List<Integer> arrayToSort) {

        List<List<Integer>> buckets = anotherSplitIntoUnsortedBuckets(arrayToSort);

        for(List<Integer> bucket  : buckets){
            bucket.sort(comparator);
        }

        return concatenateSortedBuckets(buckets);
    }

    private List<Integer> concatenateSortedBuckets(List<List<Integer>> buckets){
        List<Integer> sortedArray = new LinkedList<>();
        for(List<Integer> bucket : buckets){
            sortedArray.addAll(bucket);
        }
        return sortedArray;
    }

    private List<List<Integer>> splitIntoUnsortedBuckets(List<Integer> initialList){

        final int max = findMax(initialList);
        //开平方
        final int numberOfBuckets = (int) Math.sqrt(initialList.size());

        List<List<Integer>> buckets = new ArrayList<>();
        for(int i = 0; i < numberOfBuckets; i++) buckets.add(new ArrayList<>());

        //distribute the data
        for (int i : initialList) {
            buckets.get(hash(i, max, numberOfBuckets)).add(i);
        }
        return buckets;

    }



    private List<List<Integer>> anotherSplitIntoUnsortedBuckets(List<Integer> initialList){
        //开平方
        final int numberOfBuckets = (int) Math.sqrt(initialList.size());

        final int max = findMax(initialList);

        final int min = findMin(initialList);

        final double range = (double) (max - min + 1) / numberOfBuckets;

        List<List<Integer>> buckets = new ArrayList<>();
        for(int i = 0; i < numberOfBuckets; i++) buckets.add(new ArrayList<>());

        //distribute the data
        for (int i : initialList) {
            buckets.get(hashAnother(i, min, range)).add(i);
        }
        return buckets;

    }


    private int findMax(List<Integer> input){
        int m = Integer.MIN_VALUE;
        for (int i : input){
            m = Math.max(i, m);
        }
        return m;
    }

    private int findMin(List<Integer> input){
        int m = 0;
        for (int i : input){
            m = Math.min(i, m);
        }
        return m;
    }

    private static int hash(int i, int max, int numberOfBuckets) {
        return (int) ((double) i / max * (numberOfBuckets - 1));
    }

    private static int hashAnother(int i, int min, double range) {
        return (int)(Math.floor(i - min) / range);
    }


}

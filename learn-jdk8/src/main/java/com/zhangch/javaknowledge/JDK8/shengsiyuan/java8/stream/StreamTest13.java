package com.zhangch.javaknowledge.JDK8.shengsiyuan.java8.stream;


import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

public class StreamTest13 {

    public static void main(String[] args) {
        Student student1 = new Student("zhangsan", 100, 20);
        Student student2 = new Student("lisi", 90, 20);
        Student student3 = new Student("wangwu", 90, 30);
        Student student4 = new Student("zhangsan", 80, 40);

        List<Student> students = Arrays.asList(student1, student2, student3, student4);
//     * <pre>{@code
//     *     Map<City, Set<String>> namesByCity
//     *         = people.stream().collect(groupingBy(Person::getCity, TreeMap::new,
//                *                                              mapping(Person::getLastName, toSet())));
//     * }</pre>
        Map<String, List<String>> namesByCity = students.stream().collect(Collectors.groupingBy(Student::getName, TreeMap::new
                , mapping(Student::getName, toList())));
//        Map<String, Set<String>> map1 = students.stream().
//                collect(groupingBy(Student::getName,mapping(Student::getName),toSet()));
        Map<Integer, List<Student>> map = students.stream().
                collect(Collectors.groupingBy(Student::getScore));

        Map<String, Long> map1 = students.stream().
                collect(Collectors.groupingBy(Student::getName, Collectors.counting()));

        Map<String, Double> map3 = students.stream().
                collect(Collectors.groupingBy(Student::getName, Collectors.averagingDouble(Student::getScore)));

        Map<Boolean, List<Student>> map2 = students.stream().
                collect(Collectors.partitioningBy(student -> student.getScore() >= 90));

        System.out.println(map);


    }
}

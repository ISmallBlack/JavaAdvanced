package com.zhangch.javaknowledge.JDK8.shengsiyuan.java8.stream2;


public class Student {

    private String name;
    private  String urid;
    private int score;

    public Student(String name, String urid, int score) {
        this.name = name;
        this.score = score;
        this.urid = urid;

    }

    public String getUrid() {
        return urid;
    }

    public void setUrid(String urid) {
        this.urid = urid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}

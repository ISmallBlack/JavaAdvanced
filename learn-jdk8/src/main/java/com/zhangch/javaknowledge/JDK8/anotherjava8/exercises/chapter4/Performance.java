package com.zhangch.javaknowledge.JDK8.anotherjava8.exercises.chapter4;

import com.zhangch.javaknowledge.JDK8.anotherjava8.examples.chapter1.Artist;

import java.util.stream.Stream;

/** A Performance by some musicians - e.g., an Album or Gig. */
public interface Performance {

    public String getName();

    public Stream<Artist> getMusicians();

}
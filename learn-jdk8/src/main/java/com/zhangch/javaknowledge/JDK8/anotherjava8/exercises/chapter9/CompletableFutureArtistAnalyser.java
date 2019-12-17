package com.zhangch.javaknowledge.JDK8.anotherjava8.exercises.chapter9;

import com.zhangch.javaknowledge.JDK8.anotherjava8.examples.chapter1.Artist;
import com.zhangch.javaknowledge.JDK8.anotherjava8.exercises.Exercises;

import java.util.function.Consumer;
import java.util.function.Function;

public class CompletableFutureArtistAnalyser implements ArtistAnalyzer {

    private final Function<String, Artist> artistLookupService;

    public CompletableFutureArtistAnalyser(Function<String, Artist> artistLookupService) {
        this.artistLookupService = artistLookupService;
    }

    public void isLargerGroup(String artistName, String otherArtistName, Consumer<Boolean> handler) {
        Exercises.replaceThisWithSolution();
    }

    private long getNumberOfMembers(String artistName) {
        return Exercises.replaceThisWithSolution();
    }

}

package com.baeldung.algorithms2.astar;

public interface Scorer<T extends GraphNode> {
    double computeCost(T from, T to);
}

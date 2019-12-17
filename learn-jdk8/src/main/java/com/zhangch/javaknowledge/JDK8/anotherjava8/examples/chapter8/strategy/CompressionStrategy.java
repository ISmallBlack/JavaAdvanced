package com.zhangch.javaknowledge.JDK8.anotherjava8.examples.chapter8.strategy;

import java.io.IOException;
import java.io.OutputStream;

// BEGIN CompressionStrategy
public interface CompressionStrategy {

    public OutputStream compress(OutputStream data) throws IOException;

}
// END CompressionStrategy

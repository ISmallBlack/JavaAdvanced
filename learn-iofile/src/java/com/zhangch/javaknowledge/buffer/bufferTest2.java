package com.zhangch.javaknowledge.buffer;

import cn.hutool.core.util.ArrayUtil;

import java.nio.*;
import java.util.Arrays;

/**
 * @author 张晨辉
 * @Classname bufferTest1
 * @Description TODO
 * @Date 2020/6/16 下午2:33
 */
public class bufferTest2 {

    public static void main(String[] args) {

        char[] charArray = new char[]{'a','b','c','d','e' };

        CharBuffer buffer= CharBuffer.wrap(charArray) ;
        System.out.println("Acapacity()=" + buffer.capacity() + "limit()="+
                buffer.limit()) ;
        buffer.limit(4) ;
        System.out.println() ;
        System.out.println("Acapacity()=" + buffer.capacity() + "limit()="+
                buffer.limit()) ;
        /**
         * 到  buffer.put(4, 'r'); 报错  limit 从 0 开始
         */
//        buffer.put(0, 'o');
//        buffer.put(1, 'p');
//        buffer.put(2, 'q');
//        buffer.put(3, 'r');
//          buffer.put(4, 's');
//        buffer.put(5, 't');
        System.out.println("======================position start===========") ;
        // 什么是位置呢?它代表“下一个”要读取或写入元素的 index (索引)，
        // 缓冲区的 position (位置)不能为负 ，并且 position不能大于其 limit。
        // 如果 mark 已定义且大于新的 position, 则丢弃该 mark。
        System.out.println(Arrays.toString(charArray));
        //虽然 position<=limit 但是如果 position = limit  put数据的时候报错
        buffer.position(3) ;
        buffer.put('1');
        System.out.println(Arrays.toString(charArray));
        System.out.println("Acapacity()=" + buffer.capacity() + "limit()="+
                buffer.limit()+" position= "+buffer.position()) ;
        System.out.println("======================remaining start===========") ;
        System.out.println("C remaining ()=" + buffer.remaining()) ;


        System.out.println("======================mark start===========") ;
        //方法Buffer.mark()的作用: 在此缓冲区的位置设置标记。
        byte[] byteArray = new byte[] { 1, 2, 3 };
        ByteBuffer bytebuffer = ByteBuffer.wrap(byteArray) ;
        System.out.println("bytebuffer.capacity=" + bytebuffer.capacity()) ;
        System.out.println() ;
        bytebuffer.position(1) ;
        bytebuffer.mark(); //在位置 1 设置 mark
        System.out.println("bytebuffer.position="+ bytebuffer.position());
        bytebuffer.position(2) ; //改变位置
        System.out.println("bytebuffer.position="+ bytebuffer.position());
        bytebuffer.reset() ; //位置重置
        System.out.println();
        //回到位置为 1 处
        System . out .println( "bytebuffer.position="
                + bytebuffer.position()) ;
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect (100) ;

        //





    }

}

package com.zhangch.javaknowledge.buffer;

import java.nio.*;

/**
 * @author 张晨辉
 * @Classname bufferTest1
 * @Description TODO
 * @Date 2020/6/16 下午2:33
 */
public class bufferTest1 {

    public static void main(String[] args) {
        byte[] byteArray = new byte[] { 1, 2, 3 };
        short[] shortArray = new short[] {1, 2, 3, 4 };
        int[] intArray = new int[] { 1, 2, 3, 4, 5 };
        long[] longArray = new long[] { 1, 2, 3, 4, 5, 6 };
        float[] floatArray = new float[] { 1, 2, 3, 4, 5, 6, 7 };
        double [] doubleArray = new double [] { 1, 2, 3, 4, 5, 6, 7, 8 };
        char[] charArray = new char[]{'a','b','c', 'd' };



        ByteBuffer bytebuffer = ByteBuffer.wrap(byteArray) ;
        ShortBuffer shortBuffer = ShortBuffer.wrap(shortArray) ;
        IntBuffer intBuffer =IntBuffer.wrap (intArray) ;
        LongBuffer longBuffer = LongBuffer.wrap(longArray) ;
        FloatBuffer floatBuffer = FloatBuffer.wrap(floatArray);
        DoubleBuffer doubleBuffer = DoubleBuffer.wrap(doubleArray) ;
        CharBuffer charBuffer = CharBuffer.wrap(charArray);
        System.out.println("bytebuffer=" + bytebuffer.getClass().getName()) ;
        System.out.println( "shortBuffer=" + shortBuffer.getClass().getName()) ;
        System.out.println("intBuffer=" + intBuffer.getClass().getName());
        System.out.println("longBuffer=" + longBuffer.getClass().getName());
        System.out.println("floatBuffer=" + floatBuffer.getClass().getName()) ;
        System.out.println("doubleBuffer=" + doubleBuffer.getClass().getName());
        System.out.println("charBuffer=" + charBuffer .getClass() .getName());

        System .out.println();

        System.out.println("bytebuffer=" + bytebuffer.capacity()) ;
        System.out.println( "shortBuffer=" + shortBuffer.capacity()) ;
        System.out.println("intBuffer=" + intBuffer.capacity());
        System.out.println("longBuffer=" + longBuffer.capacity());
        System.out.println("floatBuffer=" + floatBuffer.capacity()) ;
        System.out.println("doubleBuffer=" + doubleBuffer.capacity());
        System.out.println("charBuffer=" + charBuffer.capacity());






    }

}

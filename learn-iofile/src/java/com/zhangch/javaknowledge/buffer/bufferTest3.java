package com.zhangch.javaknowledge.buffer;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.Arrays;

/**
 * @author 张晨辉
 * @Classname bufferTest1
 * @Description TODO
 * @Date 2020/6/16 下午2:33
 */
public class bufferTest3 {

    public static void main(String[] args) {
        CharBuffer charBuffer = CharBuffer.allocate(20);
        System.out.println("Aposition()=" + charBuffer.position() + "limit()=" +
                charBuffer.limit());
        //一共写入 14 个字
        charBuffer.put("我是中国人我在中华人民共和国");
        System.out.println("Bposition()=" + charBuffer.position() + "limit()=" +
                charBuffer.limit());
        charBuffer.position(0);//位置 position 还原成0
        System.out.println("Cposition()=" + charBuffer.position() + "limit()=" +
                charBuffer.limit());
        //下面 for 语句的打印效果是“国”字后面有 6 个空格，这 6 个空格是无效的数据
        // 应该只打印前 14 个字符，后 6 个字符不再读取
        int count = 0;
        for (int i = 0; i < charBuffer.limit(); i++) {
            ++count;
            System.out.print(charBuffer.get());
             //上面的代码是错误读取数据的代码
        }
        System.out.println();
        System.out.println("循环次数："+count) ;
        charBuffer.clear();
        charBuffer.put("我是中国人我在中华人民");
        charBuffer.flip();
        System.out.println("Dposition()=" + charBuffer.position() + "limit()=" +
                charBuffer.limit());
        count = 0;
        for (int i = 0; i < charBuffer.limit(); i++) {
            ++count;
            System.out.print(charBuffer.get());
            //上面的代码是错误读取数据的代码
        }
        System.out.println();
        System.out.println("循环次数："+count) ;


        System.out.println("================hasarray start================");

        ByteBuffer byteBuffer_allocate = ByteBuffer.allocate(100) ;
        byteBuffer_allocate.put( (byte ) 1) ;
        byteBuffer_allocate.put((byte) 2);
        System.out.println(byteBuffer_allocate.hasArray()) ;

        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(100) ;
        byteBuffer.put( (byte ) 1) ;
        byteBuffer.put((byte) 2);
        System.out.println(byteBuffer.hasArray()) ;

        //rewind()方法的通俗解释就是“标记清除，位置 position值归 0, limit不变” 。
        //注意 : rewind() 方法常在重新读取缓冲区中数据时使用 。
        System.out.print("================rewind start================");
    }

}

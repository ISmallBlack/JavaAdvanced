package com.zhangch.javaknowledge.wangwenjun.Second.classloader.chapter4;


import java.lang.reflect.Method;

/***************************************
 * @author:Alex Wang
 * @Date:2017/4/4 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class ClassLoaderTest {
    public static void main(String[] args) throws Exception {

        DecryptClassLoader classLoader = new DecryptClassLoader();
        Class<?> aClass = classLoader.loadClass("com.wangwenjun.concurrent.classloader.chapter3.MyObject");
        System.out.println(aClass);

        Object obj = aClass.newInstance();
        Method method = aClass.getMethod("hello", new Class<?>[]{});
        Object result = method.invoke(obj, new Object[]{});
        System.out.println(result);

    }
}
package com.zhangch.javaknowledge.TypeDes;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.List;

/**
 * 表示通配符类型，比如 <?>, <? Extends Number>等
 *
 * getLowerBounds(): Type[]: 得到下边界的数组
 * getUpperBounds(): Type[]: 得到上边界的type数组
 */
public class TestWildcardType {

    public static void main(String[] args) {
        //获取TestWildcardType类的所有方法(本例中即 testWildcardType 方法)
        Method[] methods = TestWildcardType.class.getDeclaredMethods();
        for (Method method : methods) {
            //获取方法的所有参数类型
            Type[] types = method.getGenericParameterTypes();
            for (Type paramsType : types) {
                System.out.println("type: " + paramsType.toString());
                //如果不是参数化类型则直接continue，执行下一个循环条件
                if (!(paramsType instanceof ParameterizedType)) {
                    continue;
                }
                //将当前类型强转为参数化类型并获取其实际参数类型(即含有通配符的泛型类型)
                Type type = ((ParameterizedType) paramsType).getActualTypeArguments()[0];
                //输出其是否为通配符类型
                System.out.println("type instanceof WildcardType : " +
                        (type instanceof WildcardType));
                if (type instanceof WildcardType) {
                    int lowIndex = ((WildcardType) type).getLowerBounds().length - 1;
                    int upperIndex = ((WildcardType) type).getUpperBounds().length - 1;
                    //输出上边界与下边界
                    System.out.println("getLowerBounds(): "+ lowIndex+"     "
                            +
                            (lowIndex >= 0 ? ((WildcardType) type).getLowerBounds()[lowIndex] : "String ")
                            + "; getUpperBounds(): "+upperIndex+"     "
                            +
                            (upperIndex >= 0 ? ((WildcardType) type).getUpperBounds()[upperIndex] : "Object"));
                }
            }
        }
    }

    public void testWildcardType(List<? extends OutputStream> numberList,
                                 List<? super InputStream> upperList, List<Integer> list, InputStream inputStream) {
    }
}

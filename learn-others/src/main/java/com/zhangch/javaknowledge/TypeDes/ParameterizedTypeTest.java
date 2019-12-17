package com.zhangch.javaknowledge.TypeDes;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * 功能说明: <br>
 * 系统版本: 1.0 <br>
 * 开发人员: zhanch
 * 开发时间: 2019/12/16<br>
 *
 *  参数化类型即我们通常所说的泛型类型，一提到参数，最熟悉的就是定义方法时有形参，然后调用此方法时传递实参。那么参数化类型怎么理解呢？顾名思义
 *  ，就是将类型由原来的具体的类型参数化，类似于方法中的变量参数，此时类型也定义成参数形式（可以称之为类型形参）
 *  ，然后在使用/调用时传入具体的类型（类型实参）。那么我们的ParameterizedType就是这样一个类型，下面我们来看看它的三个重要的方法：
 *
 *
 * getRawType(): Type
 * 该方法的作用是返回当前的ParameterizedType的类型。如一个List，返回的是List的Type，即返回当前参数化类型本身的Type。
 *
 *
 * getOwnerType(): Type
 * 返回ParameterizedType类型所在的类的Type。如Map.Entry<String, Object>这个参数化类型返回的事Map(因为Map.Entry这个类型所在的类是Map)的类型。
 *通过方法的名称，我们大概了解到，此方法是获取泛型的拥有者，那么拥有者是个什么意思？
 * Returns a {@code Type} object representing the type that this type    * is a member of.  For example, if this type is {@code O.I},
 * * return a representation of {@code O}.  （摘自JDK注释）
 * 通过注解，我们得知，“拥有者”表示的含义--内部类的“父类”，通过getOwnerType()方法可以获取到内部类的“拥有者”；例如： Map
 * 就是 Map.Entry<String,String>的拥有者
 *
 * getActualTypeArguments(): Type[]
 * 该方法返回参数化类型<>中的实际参数类型， 如 Map<String,Person> map 这个 ParameterizedType 返回的是 String 类,Person 类的全限定类名的 Type Array。
 * 注意: 该方法只返回最外层的<>中的类型，无论该<>内有多少个<>。

 * <br>
 */
public class ParameterizedTypeTest {
    private HashMap<String, Object> map;
    private HashSet<String> set;
    private List<String> list;
    private Class<?> clz;
    private Map.Entry<String,String> entry;
    //不是ParameterizedType
    private Integer i;
    private String str;

    public static void main(String[] args) {
        printParameterizedType();
    }
    private static void  printParameterizedType(){
        Field[] fields = ParameterizedTypeTest.class.getDeclaredFields();
        for (Field f : fields){
            //打印是否是ParameterizedType类型
            System.out.println("FieldName:  " + f.getName() + " instanceof ParameterizedType is : " +
                    (f.getGenericType() instanceof ParameterizedType));
        }
        //取map这个类型中的实际参数类型的数组
        getParameterizedTypeWithName("map");
        getParameterizedTypeWithName("str");
        getParameterizedTypeWithName("entry");

    }

    private static void getParameterizedTypeWithName(String name){
        Field f;
        try {
            //利用反射得到TestParameterizedTypeBean类中的所有变量
            f = ParameterizedTypeTest.class.getDeclaredField(name);
            f.setAccessible(true);
            Type type = f.getGenericType();
            if (type instanceof ParameterizedType){
                for(Type param : ((ParameterizedType)type).getActualTypeArguments()){
                    //打印实际参数类型
                    System.out.println("---type actualType---" + param.toString());
                }
                //打印所在的父类的类型
                System.out.println("---type ownerType0---"+ ((ParameterizedType) type).getOwnerType());
                //打印其本身的类型
                System.out.println("---type rawType---"+ ((ParameterizedType) type).getRawType());
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

}

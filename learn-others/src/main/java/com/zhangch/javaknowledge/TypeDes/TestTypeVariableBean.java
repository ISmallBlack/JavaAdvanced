package com.zhangch.javaknowledge.TypeDes;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/**
 * 范型信息在编译时会被转换为一个特定的类型, 而TypeVariable就是用来反映在JVM编译该泛型前的信息
 * 。(通俗的来说，TypeVariable就是我们常用的T，K这种泛型变量)
 *
 *
 * getBounds(): Type[]:
 * 返回当前类型的上边界，如果没有指定上边界，则默认为Object。
 *
 *
 * getName(): String:
 * 返回当前类型的类名
 *
 *
 * getGenericDeclaration(): D
 * 返回当前类型所在的类的Type。
 *

 * @param <K>
 * @param <T>
 */
public class TestTypeVariableBean<K extends Number, T> {
      
    //K有指定了上边界Number
    K key;
    //T没有指定上边界，其默认上边界为Object
    T value;
    
    public static void main(String[] args){
        //获取 泛型参数的类型变量
  		Type[] types = TestTypeVariableBean.class.getTypeParameters();
  		for (Type type : types){
            TypeVariable t = (TypeVariable) type;
            int index = t.getBounds().length - 1;
            //输出上边界
            System.out.println("--getBounds()-- " + t.getBounds()[index]);
            //输出名称
            System.out.println("--getName()--" + t.getName());
            //输出所在的类的类型
            System.out.println("--getGenericDeclaration()--" + t.getGenericDeclaration());
  		}
    }
  }

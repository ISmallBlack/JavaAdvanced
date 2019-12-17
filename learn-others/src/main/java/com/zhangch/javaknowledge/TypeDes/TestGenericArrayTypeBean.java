package com.zhangch.javaknowledge.TypeDes;

import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.util.List;

/**
 * 组成数组的元素中有泛型则实现了该接口; 它的组成元素是 ParameterizedType 或 TypeVariable 类型。
 * (通俗来说，就是由参数类型组成的数组。如果仅仅是参数化类型，则不能称为泛型数组，而是参数化类型)。
 * 注意：无论从左向右有几个[]并列，这个方法仅仅脱去最右边的[]之后剩下的内容就作为这个方法的返回值。
 *
 * getGenericComponentType(): Type:
 *
 * 返回组成泛型数组的实际参数化类型，如List[] 则返回 List。
 *
 * @param <T>
 */
public class TestGenericArrayTypeBean<T> {
      
    //泛型数组类型  TypeVariable
    private T[] value;
    // ParameterizedType
    private List<String>[] list;
  
    //不是泛型数组类型
    private List<String> singleList;
    private T singleValue;
  
    public static void main(String[] args){
  	Field[] fields = TestGenericArrayTypeBean.class.getDeclaredFields();
  	for (Field field: fields){
          field.setAccessible(true);
        //输出当前变量是否为GenericArrayType类型
          System.out.println("Field: "
              + field.getName()
              + "; instanceof GenericArrayType"
              + ": "
              + (field.getGenericType() instanceof GenericArrayType));
          if (field.getGenericType() instanceof GenericArrayType){
            //如果是GenericArrayType，则输出当前泛型类型
            System.out.println("Field: "
                + field.getName()
                + "; getGenericComponentType()"
                + ": "
                + (((GenericArrayType) field.getGenericType()).getGenericComponentType()));
          	   }
  			  }
    	}
  	}


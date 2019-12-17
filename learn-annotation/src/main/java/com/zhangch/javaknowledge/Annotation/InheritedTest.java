package com.zhangch.javaknowledge.Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class InheritedTest {

    @Target(value = {ElementType.METHOD, ElementType.FIELD})
    @Retention(value = RetentionPolicy.RUNTIME)
    @interface DESC {
        String value() default "";
    }

    class SuperClass {
        @DESC("父类方法foo")
        public void foo() {
        }

        @DESC("父类方法bar")
        public void bar() {
        }

        @DESC("父类的属性")
        public String field;
    }

    class ChildClass extends SuperClass {
        @Override
        public void foo() {
            super.foo();
        }
    }
   //方法和属性上注解的继承，忠实于方法/属性继承本身，客观反映方法/属性上的注解。
    public static void main(String[] args) throws NoSuchMethodException, NoSuchFieldException {
        Method foo = ChildClass.class.getMethod("foo");
        System.out.println(Arrays.toString(foo.getAnnotations()));
        // output: []
        // 子类ChildClass重写了父类方法foo,并且@Override注解只在源码阶段保留，所以没有任何注解

        Method bar = ChildClass.class.getMethod("bar");
        System.out.println(Arrays.toString(bar.getAnnotations()));
        // output: [@annotations.InheritedTest$DESC(value=父类方法bar)]
        // bar方法未被子类重写，从父类继承到了原本注解

        Field field = ChildClass.class.getField("field");
        System.out.println(Arrays.toString(field.getAnnotations()));
        // output: [@annotations.InheritedTest$DESC(value=父类的属性)]
        // 解释同上
    }
}


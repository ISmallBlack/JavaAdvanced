package com.zhangch.javaknowledge.Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class IterInheritedTest {

    @Target(value = {ElementType.METHOD, ElementType.FIELD})
    @Retention(value = RetentionPolicy.RUNTIME)
    @interface DESC {
        String value() default "";
    }

    interface SuperInterface {
        @DESC("父接口的属性")
        String field = "field";
        @DESC("父接口方法foo")
        public void foo();
        @DESC("父接口方法bar")
        default public void bar() {

        }
    }

    interface ChildInterface extends SuperInterface {
        @DESC("子接口方法foo")
        @Override
        void foo();
    }

    class ChildClass implements SuperInterface {
        @DESC("子类的属性")
        public String field = "field";
        @Override
        public void foo() {
        }
    }

    public static void main(String[] args) throws NoSuchMethodException, NoSuchFieldException {
        Method iFoo = ChildInterface.class.getMethod("foo");
        System.out.println(Arrays.toString(iFoo.getAnnotations()));
        // output: [@annotations.IterInheritedTest$DESC(value=子接口方法foo)]
        // 重写了父接口方法 子接口会替换
        Method iBar = ChildInterface.class.getMethod("bar");
        System.out.println(Arrays.toString(iBar.getAnnotations()));
        // output: [@annotations.IterInheritedTest$DESC(value=父接口方法bar)]

        Field iField = ChildInterface.class.getField("field");
        System.out.println(Arrays.toString(iField.getAnnotations()));
        // output: [@annotations.IterInheritedTest$DESC(value=父接口的属性)]

        Method foo = ChildClass.class.getMethod("foo");
        System.out.println(Arrays.toString(foo.getAnnotations()));
        // output: []; 被子类覆盖

        Method bar = ChildClass.class.getMethod("bar");
        System.out.println(Arrays.toString(bar.getAnnotations()));
        // output: [@annotations.IterInheritedTest$DESC(value=父接口方法bar)]

        Field field = ChildClass.class.getField("field");
        System.out.println(Arrays.toString(field.getAnnotations()));
        // output: [@annotations.IterInheritedTest$DESC(value=子类的属性)]
        // 是子类作用域下的属性`field`
    }
}
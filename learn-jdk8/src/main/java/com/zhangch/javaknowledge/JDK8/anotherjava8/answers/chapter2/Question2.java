package com.zhangch.javaknowledge.JDK8.anotherjava8.answers.chapter2;

import java.text.SimpleDateFormat;
import javax.swing.text.DateFormatter;

public class Question2 {

     public final static ThreadLocal<DateFormatter> formatter = ThreadLocal.withInitial(() -> new DateFormatter(new SimpleDateFormat("dd-MMM-yyyy")));

}

package com.zhangch.javaknowledge.JDK8.anotherjava8.examples.chapter8.template_method.lambdas;

import com.zhangch.javaknowledge.JDK8.anotherjava8.examples.chapter8.template_method.ApplicationDenied;

// BEGIN Criteria
public interface Criteria {

    public void check() throws ApplicationDenied;

}
// END Criteria

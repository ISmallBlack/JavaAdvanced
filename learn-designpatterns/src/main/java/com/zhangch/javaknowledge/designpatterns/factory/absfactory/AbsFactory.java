package com.zhangch.javaknowledge.designpatterns.factory.absfactory;

import com.zhangch.javaknowledge.designpatterns.factory.pizza.Pizza;

public interface AbsFactory {
	public Pizza CreatePizza(String ordertype) ;
}

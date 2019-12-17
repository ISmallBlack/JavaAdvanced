package com.zhangch.javaknowledge.designpatterns.factory.absfactory;


import com.zhangch.javaknowledge.designpatterns.factory.pizza.*;
import com.zhangch.javaknowledge.designpatterns.factory.pizza.Pizza;

public class NYFactory implements AbsFactory {

	
	@Override
	public Pizza CreatePizza(String ordertype) {
		Pizza pizza = null;

		if (ordertype.equals("cheese")) {
			pizza = new NYCheesePizza();
		} else if (ordertype.equals("pepper")) {
			pizza = new NYPepperPizza();
		}
		return pizza;

	}

}

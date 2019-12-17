package com.zhangch.javaknowledge.designpatterns.factory.absfactory;


import com.zhangch.javaknowledge.designpatterns.factory.pizza.*;
import com.zhangch.javaknowledge.designpatterns.factory.pizza.Pizza;

public class LDFactory implements AbsFactory {

	@Override
	public Pizza CreatePizza(String ordertype) {
		Pizza pizza = null;

		if (ordertype.equals("cheese")) {
			pizza = new LDCheesePizza();
		} else if (ordertype.equals("pepper")) {
			pizza = new LDPepperPizza();
		}
		return pizza;

	}

}

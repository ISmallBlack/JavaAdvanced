package com.zhangch.javaknowledge.designpatterns.factory.method;


import com.zhangch.javaknowledge.designpatterns.factory.pizza.LDCheesePizza;
import com.zhangch.javaknowledge.designpatterns.factory.pizza.LDPepperPizza;
import com.zhangch.javaknowledge.designpatterns.factory.pizza.Pizza;

public class LDOrderPizza extends OrderPizza {

	@Override
	Pizza createPizza(String ordertype) {
		Pizza pizza = null;

		if (ordertype.equals("cheese")) {
			pizza = new LDCheesePizza();
		} else if (ordertype.equals("pepper")) {
			pizza = new LDPepperPizza();
		}
		return pizza;

	}

}

package com.zhangch.javaknowledge.designpatterns.factory.method;


import com.zhangch.javaknowledge.designpatterns.factory.pizza.NYCheesePizza;
import com.zhangch.javaknowledge.designpatterns.factory.pizza.NYPepperPizza;
import com.zhangch.javaknowledge.designpatterns.factory.pizza.Pizza;

public class NYOrderPizza extends OrderPizza {

	@Override
	Pizza createPizza(String ordertype) {
		Pizza pizza = null;

		if (ordertype.equals("cheese")) {
			pizza = new NYCheesePizza();
		} else if (ordertype.equals("pepper")) {
			pizza = new NYPepperPizza();
		}
		return pizza;

	}

}

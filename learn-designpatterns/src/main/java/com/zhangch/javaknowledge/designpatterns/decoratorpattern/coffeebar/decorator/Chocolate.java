package com.zhangch.javaknowledge.designpatterns.decoratorpattern.coffeebar.decorator;


import com.zhangch.javaknowledge.designpatterns.decoratorpattern.coffeebar.Drink;

public class Chocolate extends Decorator {

	public Chocolate(Drink Obj) {
		super(Obj);
		// TODO Auto-generated constructor stub
		super.setDescription("Chocolate");
		super.setPrice(3.0f);
	}

}

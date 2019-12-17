package com.zhangch.javaknowledge.designpatterns.decoratorpattern.coffeebar.coffee;


import com.zhangch.javaknowledge.designpatterns.decoratorpattern.coffeebar.Drink;

public  class Coffee extends Drink {

	@Override
	public float cost() {
		// TODO Auto-generated method stub
		return super.getPrice();
	}

	
}

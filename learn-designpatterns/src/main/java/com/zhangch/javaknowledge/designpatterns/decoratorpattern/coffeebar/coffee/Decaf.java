package com.zhangch.javaknowledge.designpatterns.decoratorpattern.coffeebar.coffee;

public class Decaf extends Coffee {
	public Decaf()
	{
		super.setDescription("Decaf");
		super.setPrice(3.0f);
	}
}

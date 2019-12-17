package com.zhangch.javaknowledge.designpatterns.decoratorpattern.coffeebar.coffee;

public class Espresso extends Coffee{
	
	public Espresso()
	{
		super.setDescription("Espresso");
		super.setPrice(4.0f);
	}

}

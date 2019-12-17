package com.zhangch.javaknowledge.designpatterns.decoratorpattern.coffeebar;


import com.zhangch.javaknowledge.designpatterns.decoratorpattern.coffeebar.coffee.Decaf;
import com.zhangch.javaknowledge.designpatterns.decoratorpattern.coffeebar.coffee.LongBlack;
import com.zhangch.javaknowledge.designpatterns.decoratorpattern.coffeebar.decorator.Chocolate;
import com.zhangch.javaknowledge.designpatterns.decoratorpattern.coffeebar.decorator.Milk;

public class CoffeeBar {


	public static void main(String[] args) {
		
		Drink order;
		order=new Decaf();
		System.out.println("order1 price:"+order.cost());
		System.out.println("order1 desc:"+order.getDescription());
		
		System.out.println("****************");
		order=new LongBlack();
		order=new Milk(order);
		order=new Chocolate(order);
		order=new Chocolate(order);
		System.out.println("order2 price:"+order.cost());
		System.out.println("order2 desc:"+order.getDescription());
		
	}


}

package com.zhangch.javaknowledge.designpatterns.strategypattern.duck;


import com.zhangch.javaknowledge.designpatterns.strategypattern.flybehavior.BadFlyBehavior;
import com.zhangch.javaknowledge.designpatterns.strategypattern.quackbehavior.GeGeQuackBehavior;

public class RedHeadDuck extends Duck {

	public RedHeadDuck() {
		mFlyBehavior = new BadFlyBehavior();
		mQuackBehavior = new GeGeQuackBehavior();
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		System.out.println("**RedHead**");
	}

}

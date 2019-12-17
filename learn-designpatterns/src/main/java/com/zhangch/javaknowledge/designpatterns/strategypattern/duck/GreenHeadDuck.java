package com.zhangch.javaknowledge.designpatterns.strategypattern.duck;


import com.zhangch.javaknowledge.designpatterns.strategypattern.flybehavior.GoodFlyBehavior;
import com.zhangch.javaknowledge.designpatterns.strategypattern.quackbehavior.GaGaQuackBehavior;

public class GreenHeadDuck extends Duck {

	public GreenHeadDuck() {
		mFlyBehavior = new GoodFlyBehavior();
		mQuackBehavior = new GaGaQuackBehavior();
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		System.out.println("**GreenHead**");
	}

}
package com.zhangch.javaknowledge.designpatterns.strategypattern;


import com.zhangch.javaknowledge.designpatterns.strategypattern.duck.Duck;
import com.zhangch.javaknowledge.designpatterns.strategypattern.duck.GreenHeadDuck;
import com.zhangch.javaknowledge.designpatterns.strategypattern.duck.RedHeadDuck;
import com.zhangch.javaknowledge.designpatterns.strategypattern.flybehavior.NoFlyBehavior;
import com.zhangch.javaknowledge.designpatterns.strategypattern.quackbehavior.NoQuackBehavior;

public class StimulateDuck {

	public static void main(String[] args) {

		Duck mGreenHeadDuck = new GreenHeadDuck();
		Duck mRedHeadDuck = new RedHeadDuck();

		mGreenHeadDuck.display();
		mGreenHeadDuck.Fly();
		mGreenHeadDuck.Quack();
		mGreenHeadDuck.swim();

		mRedHeadDuck.display();
		mRedHeadDuck.Fly();
		mRedHeadDuck.Quack();
		mRedHeadDuck.swim();
		mRedHeadDuck.display();
		mRedHeadDuck.SetFlyBehavoir(new NoFlyBehavior());
		mRedHeadDuck.Fly();
		mRedHeadDuck.SetQuackBehavoir(new NoQuackBehavior());
		mRedHeadDuck.Quack();
	}

}

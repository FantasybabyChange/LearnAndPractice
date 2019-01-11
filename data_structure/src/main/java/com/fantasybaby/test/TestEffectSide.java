package com.fantasybaby.test;

import org.junit.Test;

public class TestEffectSide {
	//明显  java编译和c编译是不一样的
	@Test
	public void test() {
		int i = 1;
		System.out.println("i:" + i + " i++:" + i++);
		 i = 1;
		System.out.println("i:" + i + " ++i:" + ++i);
		i = 1;
		System.out.println("++i" + ++i + " i:" + i);
		i = 1;
		System.out.println("i++" + i++ + " i:" + i);
	}

}

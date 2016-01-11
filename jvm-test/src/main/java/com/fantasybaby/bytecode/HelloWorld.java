package com.fantasybaby.bytecode;

import com.fantasybaby.inter.HelloWorldInterface;

public class HelloWorld implements HelloWorldInterface{
	private  int b = 1001;
	public static final String str = "testFantasyBaby";
	@Deprecated
	public static final String str1 = "testFantasyBabyDeprecated";
		public static void main(String[] args) {
			System.out.println("Hello World");
		}
}

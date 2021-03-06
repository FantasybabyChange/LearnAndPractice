package com.fantasybaby;

import org.junit.Test;

import com.fantasybaby.classloader.ClassloaderSequence;

public class TestSequenceClassLoader {
	ClassloaderSequence cs = new ClassloaderSequence();
	@Test
	public void text1(){
			System.out.println(cs.count);
			System.out.println(cs.count1);
	}
	@Test
	public void text2() throws ClassNotFoundException{
		Class<?> cl = Class.forName("java.lang.String");
		System.out.println(cl.getClass().getClassLoader()); 
		Class<?> cl1 = Class.forName("com.fantasybaby.classloader.ClassloaderSequence");
		System.out.println(cl1.getClassLoader());
	}
}

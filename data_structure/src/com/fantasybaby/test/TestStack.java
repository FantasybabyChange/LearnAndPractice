package com.fantasybaby.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.fantasybaby.inter.AbstractStack;
import com.fantasybaby.inter.impl.SimpleSQLStack;

public class TestStack {

	@Test
	public void test() {
		AbstractStack<Integer> as = new SimpleSQLStack<Integer>(5);
		long currentTimeMillis = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			as.push(i);
		}
		System.out.println(System.currentTimeMillis() - currentTimeMillis);
//		while(!as.empty()){
////			System.out.println(as.pop());
//			
//		}
		
	}

}

package com.fantasybaby.hibernateValidtor;

import org.junit.Test;

import com.fantasybaby.threadTest.UserLoginThread;

public class TestThread {

	@Test
	public void test() {
		for (int i = 0; i < 4; i++) {
			new Thread(new UserLoginThread()).start();
			
		}
	}

}

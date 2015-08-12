package com.fantasybaby.test;

import org.junit.Test;

import com.fantasybaby.algorithm.ResetBitNum;
import com.fantasybaby.algorithm.ShareApple;

public class Test1 {

	@Test
	public void test() throws Exception {
		ShareApple share = new ShareApple();
		System.out.println(share.getCategoryNum());
	}
	@Test
	public void test1() throws Exception {
		ResetBitNum resetBit = new ResetBitNum();
		System.out.println(resetBit.resetTheDig());
	}

}

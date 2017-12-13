package com.fantasybaby.normaltest.egg;
/**
 * 鸡蛋问题
 * @author 曦
 *
 */
public class TestEggQuesion {
	public static int getEggQuestion(){
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			if(i%2== 1 && i%3 == 0 && i %4 ==1 
					&& i%5 == 1 && i%6 ==3 
					&& i%7 == 0 && i%8 == 1 
					&& i %9 == 0){
				System.out.println(i);
				return i;
			}
		}
		return 0;
	}
	public static void main(String[] args) {
		getEggQuestion();
	}
}

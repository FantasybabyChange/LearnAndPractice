package com.fantasybaby.threeItem;

import com.fantasybaby.bean.JsonBean;

public class TestThreeItemNullPointer {
	static JsonBean jsonB = new JsonBean();
	public static void haveNullPointer(){
		Object ob = jsonB != null ? jsonB.getInteNum():0;
	}
	
	public static void resoloveNullPointer(){
		Object ob = jsonB != null ? jsonB.getInteNum():Integer.valueOf(0);	
		}
	public static void main(String[] args) {
		jsonB.setInteNum(null); 
//		haveNullPointer();
		resoloveNullPointer();
		
	}
}

package com.fantasybaby.normaltest.threeItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fantasybaby.convert.bean.JsonBean;

public class TestThreeItemNullPointer {
	private static Logger _logger = LoggerFactory.getLogger(TestThreeItemNullPointer.class);
	static JsonBean jsonB = new JsonBean();
	public static void haveNullPointer(){
		Object ob = jsonB != null ? jsonB.getInteNum():0;
	}
	
	public static void resoloveNullPointer(){
		Object ob = jsonB != null ? jsonB.getInteNum():Integer.valueOf(0);	
		}
	public static void main(String[] args) {
		_logger.info("heheh");
		jsonB.setInteNum(null); 
//		haveNullPointer();
		resoloveNullPointer();
		
	}
	
}

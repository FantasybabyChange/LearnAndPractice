package com.fantasybaby.bytecode;
/**
 * 
 * @author 曦 FantasyBaby
 *
 */
public class MethodInfo {
	
	public String sayHello(String str){
		return str;
	}
	
	public  static void sayStaticHello(String str){
		System.out.println(str);
	}
	private void sayPrivilgeHello(String str){
	    System.out.println(str);
	}
	 void sayPrivilgeHello(int value){
	    value ++;
	    System.out.println(value);
	}

}

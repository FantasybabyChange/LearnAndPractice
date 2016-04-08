package com.fantasybaby.enumP;

public enum HelloEnum {
	HELLO("你好"),
	FINE("好的");
	private String value;
	HelloEnum(String str){
		this.value = str;
	}
	public String getValue(){
		return value;
	}
}

package com.fantasybaby.rcp;

public enum ShellPositionEnum {
	TOP_LEFT(0),
	TOP_MID(1),
	TOP_RIGHT(2),
	MID_LEFT(3),
	MID_MID(4),
	MID_RIGTH(5),
	BOT_LEFT(7),
	BOT_MID(8),
	BOT_RIGTH(9);
	int value ;
	ShellPositionEnum(int value){
		this.value = value;
	}
	public int getValue(){
		return this.value;
	}
}

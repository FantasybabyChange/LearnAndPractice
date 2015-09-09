package com.fantasybaby.constant;
/**
 * the value show the priority about the character
 * 0-5
 * @author FantasyBaby
 *
 */
public enum ExpressionOper {
	DIGITAL(0),
	ADD(1),
	SUBSTRACT(2),
	MULTIPLY(3),
	DIVIDE(4),
	RBRACKET(5),
	LBRACKET(5),
	NUMBERSIGN(6);
	private final int value;
	ExpressionOper(int value){
		this.value = value;
	}	
	public int getValue(){
		return value;
	}
}

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
	SUBSTRACT(1),
	MULTIPLY(2),
	DIVIDE(2),
	LBRACKET(3),
	RBRACKET(4),
	NUMBERSIGN(5);
	private final int value;
	ExpressionOper(int value){
		this.value = value;
	}	
	public int getValue(){
		return value;
	}
}

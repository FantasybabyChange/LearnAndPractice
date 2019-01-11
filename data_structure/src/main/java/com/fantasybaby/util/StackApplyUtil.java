package com.fantasybaby.util;

import com.fantasybaby.constant.ExpressionOper;
import com.fantasybaby.exception.FantasyBabyException;
import com.fantasybaby.inter.AbstractStack;
import com.fantasybaby.inter.impl.SimpleSQLStack;

/**
 * 
 * @author FantasyBaby
 *
 */
public class StackApplyUtil {
	private static final int LARGE_THAN = 1;
	private static final int LESS_THAN = -1;
	private static final int EQUAL = 0;
	public static int getValueByChar(char value){
		int returnValue = ExpressionOper.NUMBERSIGN.getValue();
		if (Character.isDigit(value)) {
			returnValue = ExpressionOper.DIGITAL.getValue();
		}else{
			switch(value){
				case '+':
					returnValue = ExpressionOper.ADD.getValue();
				   break;
				case '-':
					returnValue = ExpressionOper.SUBSTRACT.getValue();
				   break;
				case '*':
					returnValue = ExpressionOper.MULTIPLY.getValue();
				   break;
				case '/':
					returnValue = ExpressionOper.DIVIDE.getValue();
				   break;
				case '(':
					returnValue = ExpressionOper.LBRACKET.getValue();
				   break;
				case ')':
					returnValue = ExpressionOper.RBRACKET.getValue();
				   break;
				case '#':
					returnValue = ExpressionOper.NUMBERSIGN.getValue();
				   break;
				default:
					break;
			}
		}
		return returnValue;
	}
	public static int operNum(int a,int b,char operChar){
		int sum = 0;
		switch(operChar){
			case '+':
				sum = a + b;
				break;
			case '-':
				sum = a - b;
				break;
			case '*':
				sum = a * b;
				break;
			case '/':
				sum = a / b;
				break;
		}
		return sum;
	}
	/**
	 * old version
	 * @param first
	 * @param second
	 * @return
	 */
	@Deprecated
	public static int precedeOper(int first, int second){
		int result = EQUAL;
		if (first == second) {
			result =  LARGE_THAN;
		}else if(first < second){
				result = LESS_THAN;
		}else if(first > second){
			if (first == ExpressionOper.LBRACKET.getValue()) {
				result =  LESS_THAN;
			}else if(first == ExpressionOper.NUMBERSIGN.getValue()){
				result =  LESS_THAN;
			}else{
				result =  LARGE_THAN;
			}
		}
		return result;
	}
	/**
	 * new logic for analyzing priority
	 * @param first
	 * @param second
	 * @return 0:"=",1:">", -1:"<"
	 */
	public static int precede(int first, int second){
		int result = EQUAL;
		if (first == ExpressionOper.LBRACKET.getValue() || second == ExpressionOper.LBRACKET.getValue() ) {
			result = LESS_THAN;
		}else{
			if (first == second) {
				result =  LARGE_THAN;
			}else if(first < second){
					result = LESS_THAN;
			}else if(first > second){
					result =  LARGE_THAN;
			}
		}
		return result;
	}
	/**
	 * Use the method to deal with the split 
	 * the String then  analytic the char is digital
	 * or  symbol
	 * @return
	 * @throws FantasyBabyException 
	 */
	@Deprecated
	public static String evaluateExpression(String str) throws FantasyBabyException{
		AbstractStack<Character> oper = new SimpleSQLStack<Character>();
		AbstractStack<Integer> data = new SimpleSQLStack<Integer>();
		char[] charArray = str.toCharArray();
		int count = 0;
		if (charArray.length == 0||charArray.length > 0&&getValueByChar(charArray[0]) > 0&&getValueByChar(charArray[0]) < 3) {
			throw new FantasyBabyException("expression format exceptiion");
		}
		oper.push('#');
		while (count !=charArray.length && !oper.empty()) {
			char currentValue = charArray[count];
			int propertyValue = getValueByChar(currentValue);
			if (propertyValue == ExpressionOper.DIGITAL.getValue()) {
				data.push(Integer.parseInt(String.valueOf(currentValue)));
			}else{
				char stackTopValue = oper.top();
				int stackToPProperty = getValueByChar(stackTopValue);
				if (precedeOper(stackToPProperty, propertyValue) == LARGE_THAN) {
					Integer second = data.pop();
					Integer first = data.pop();
					stackTopValue = oper.pop();
					data.push(operNum(first,second,stackTopValue));
					oper.push(currentValue);
				}else if(precedeOper(stackToPProperty, propertyValue) == LESS_THAN){
					if (propertyValue == ExpressionOper.RBRACKET.getValue()) {
						stackTopValue = oper.pop();
						while (stackToPProperty != ExpressionOper.LBRACKET.getValue()) {
							Integer second = data.pop();
							Integer first = data.pop();
							stackToPProperty = getValueByChar(stackTopValue);
							data.push(operNum(first,second,stackTopValue));
							stackTopValue = oper.pop();
							stackToPProperty = getValueByChar(stackTopValue);
						}
					}else{
						oper.push(currentValue);
					}
				}
			}
			if(count < charArray.length){
				count ++;
			}
			
		}
		if (oper.top() != ExpressionOper.NUMBERSIGN.getValue()) {
			Integer second = data.pop();
			Integer first = data.pop();
			data.push(operNum(first,second,oper.pop()));
		}
		while(!data.empty()){
			System.out.println(data.pop());
		}
		System.out.println("-----"+data.getLength());
		while(!oper.empty()){
			System.out.println(oper.pop());
		}
		return null;
	}
	/**
	 * Use the method to deal with the split 
	 * the String then  analytic the char is digital
	 * or  symbol
	 * @return the expression's result
	 * @throws FantasyBabyException 
	 */	
	public static String evaluate_expression(String str) throws FantasyBabyException{
		AbstractStack<Character> oper = new SimpleSQLStack<Character>();
		AbstractStack<Integer> data = new SimpleSQLStack<Integer>();
		char[] charArray = str.toCharArray();
		int count = 0;
		if (charArray.length == 0||charArray.length > 0&&getValueByChar(charArray[0]) > 0&&getValueByChar(charArray[0]) < 3) {
			throw new FantasyBabyException("expression format exceptiion");
		}
		oper.push('#');
		while (count < charArray.length && !oper.empty()) {
			char currentValue = charArray[count];
			int propertyValue = getValueByChar(currentValue);
			if (propertyValue == ExpressionOper.DIGITAL.getValue()) {
				data.push(Integer.parseInt(String.valueOf(currentValue)));
			}else{
				char stackTopValue = oper.top();
				int stackToPProperty = getValueByChar(stackTopValue);
				if (precedeOper(stackToPProperty, propertyValue) == LARGE_THAN) {
					Integer second = data.pop();
					Integer first = data.pop();
					stackTopValue = oper.pop();
					data.push(operNum(first,second,stackTopValue));
					oper.push(currentValue);
				}else if(precedeOper(stackToPProperty, propertyValue) == LESS_THAN){
					if (propertyValue == ExpressionOper.RBRACKET.getValue()) {
						stackTopValue = oper.pop();
						while (stackToPProperty != ExpressionOper.LBRACKET.getValue()) {
							Integer second = data.pop();
							Integer first = data.pop();
							stackToPProperty = getValueByChar(stackTopValue);
							data.push(operNum(first,second,stackTopValue));
							stackTopValue = oper.pop();
							stackToPProperty = getValueByChar(stackTopValue);
						}
					}else{
						oper.push(currentValue);
					}
				}
			}
			if(count < charArray.length -1 ){
				count ++;
			}
			
		}
		if (oper.top() != ExpressionOper.NUMBERSIGN.getValue()) {
			Integer second = data.pop();
			Integer first = data.pop();
			data.push(operNum(first,second,oper.pop()));
		}
		while(!data.empty()){
			System.out.println(data.pop());
		}
		System.out.println("-----"+data.getLength());
		while(!oper.empty()){
			System.out.println(oper.pop());
		}
		return null;
	}

}

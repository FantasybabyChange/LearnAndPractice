package com.fantasybaby.util;

import com.fantasybaby.constant.ExpressionOper;
import com.fantasybaby.inter.AbstractStack;
import com.fantasybaby.inter.impl.SimpleSQLStack;

/**
 * 
 * @author FantasyBaby
 *
 */
public class StackApplyUtil {
	private static final char[] opers = {'+','-','*','/','(',')','#'};
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
					returnValue = ExpressionOper.RBRACKET.getValue();
				   break;
				case ')':
					returnValue = ExpressionOper.LBRACKET.getValue();
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
	public int operNum(int a,int b,int operChar){
		int sum = 0;
		switch(operChar){
			case 1:
				sum = a + b;
				break;
			case 2:
				sum = a - b;
				break;
			case 3:
				sum = a * b;
				break;
			case 4:
				sum = a / b;
				break;
		}
		return sum;
	}
	/**
	 * Use the method to deal with the split 
	 * the String then  analytic the char is digital
	 * or  symbol
	 * @return
	 */
	public static String evaluateExpression(String str){
		AbstractStack<String> oper = new SimpleSQLStack<String>();
		AbstractStack<Integer> data = new SimpleSQLStack<Integer>();
		StringBuffer expression = new StringBuffer(str);
		int count = 0;
		/*if (getValueByChar(expression.substring(0,1)) > 0) {
			
		}*/
		int expressionLength = expression.length();	
		while (count < expressionLength) {
			String tmpStr = expression.substring(0,1);
			char currentValue = tmpStr.charAt(0);
			int propertyValue = getValueByChar(currentValue);
			if (propertyValue == 0) {
				data.push(Integer.parseInt(currentValue+""));
			}
			expression.deleteCharAt(0);
			count ++;
		}
		while(!data.empty()){
			System.out.println(data.pop());
		}
		System.out.println("-----"+data.getLength());
		return null;
	}

}
